package com.goda.newstk.features.news.di

import com.goda.newstk.data.localDb.ArticlesDao
import com.goda.newstk.di.ApiInfo
import com.goda.newstk.di.WithInterceptorOkHttpClient
import com.goda.newstk.features.news.data.remote.service.NewsApi
import com.goda.newstk.features.news.data.repository.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@InstallIn(SingletonComponent::class)
@Module
object NewsDiModule {
    @Provides
    @Singleton
    fun providepaymentRepository(api: NewsApi, dao: ArticlesDao,@ApiInfo  apiKey: String): NewsRepo = NewsRepo(api, dao,apiKey)

    @Provides
    @Singleton
    fun providesPaymentService(@WithInterceptorOkHttpClient retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)
    /*   @Provides
       @Singleton
       fun providePaymentRemoteDataSource(service: PaymentService,@CoroutinesIO context: CoroutineContext): PaymentRemoteDataSource {
           return PaymentRemoteDataSourceImpl(service ,context)
       }*/

}