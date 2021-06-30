package com.goda.newstk.di

import com.goda.newstk.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        return interceptor
    }

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }
    @Provides
    @WithInterceptorOkHttpClient
    @Singleton
    fun provideNetworkIntercepter(
         loggingInterceptor:HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

            .build()
    }


  @Provides
    @Singleton
    @OtherInterceptorOkHttpClient

    fun provideOtherRetrofit(@OtherInterceptorOkHttpClient okHttpClient: OkHttpClient): Retrofit{

        return Retrofit.Builder()
           /* .baseUrl(BuildConfig.BASE_URL)*/
            .addConverterFactory(GsonConverterFactory.create(provideGson()))

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           /* .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())*/

            .addConverterFactory(ScalarsConverterFactory.create())

            .client(okHttpClient)
            .build()
    }

    @CoroutinesIO
    @Provides
    @Singleton

    fun providesIoDispatcher(): CoroutineContext {
        return IO
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WithInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient