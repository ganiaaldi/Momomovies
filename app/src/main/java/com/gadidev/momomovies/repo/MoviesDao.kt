package com.gadidev.momomovies.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gadidev.momomovies.model.Movies
import com.gadidev.momomovies.model.ResultMovies

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getComingSoon() : LiveData<List<ResultMovies>>

    @Query("SELECT * FROM movies WHERE title = :title")
    fun getMovies(title: String): LiveData<ResultMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<ResultMovies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: ResultMovies)


}