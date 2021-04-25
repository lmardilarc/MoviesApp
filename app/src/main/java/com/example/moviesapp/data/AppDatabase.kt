 package com.example.moviesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapp.data.entity.MovieEntity
import com.example.moviesapp.domain.MovieDao

@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE
                    ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MoviesApp").build();
            return INSTANCE!!
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}