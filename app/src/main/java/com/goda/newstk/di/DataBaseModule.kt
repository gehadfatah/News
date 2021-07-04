package com.goda.newstk.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.goda.newstk.data.localDb.ArticlesDao
import com.goda.newstk.data.localDb.ArticlesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext appContext: Context): ArticlesDatabase {
        return Room.databaseBuilder(appContext, ArticlesDatabase::class.java, "articlesDb")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(dp: ArticlesDatabase): ArticlesDao {
        return dp.newsDataDao()
    }
}