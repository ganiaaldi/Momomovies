package com.gadidev.momomovies.services

import timber.log.Timber
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val api: ApiService
): BaseDataSource() {

    suspend fun getComingSoon() = getResult { api.getComingSoon() }

//    suspend fun getCharacter(id: Int) = getResult { api.getCharacter(id) }
}