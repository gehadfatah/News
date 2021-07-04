package com.goda.newstk.data.localDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 1)
abstract class ArticlesDatabase: RoomDatabase() {
    abstract fun newsDataDao(): ArticlesDao
}