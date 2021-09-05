package com.gadidev.momomovies.repo

import com.gadidev.momomovies.services.MoviesRemoteDataSource
import com.gadidev.momomovies.utils.performGetOperation
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesDao
) {

//    fun getCharacter(id: Int) = performGetOperation(
//        databaseQuery = { localDataSource.getCharacter(id) },
//        networkCall = { remoteDataSource.getCharacter(id) },
//        saveCallResult = { localDataSource.insert(it) }
//    )

    fun getComingSoon() = performGetOperation(
        databaseQuery = { localDataSource.getComingSoon() },
        networkCall = { remoteDataSource.getComingSoon() },
        saveCallResult = { localDataSource.insertAll(it.result) }
    )
}