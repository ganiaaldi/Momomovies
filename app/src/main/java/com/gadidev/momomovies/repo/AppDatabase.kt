package com.gadidev.momomovies.repo

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gadidev.momomovies.model.Movies
import com.gadidev.momomovies.model.ResultMovies
import com.gadidev.momomovies.utils.Converters

@Database(entities = [ResultMovies::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "momomovies_db")
                .fallbackToDestructiveMigration()
                .build()
    }

}