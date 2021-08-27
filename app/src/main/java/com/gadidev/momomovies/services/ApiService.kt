package com.gadidev.momomovies.services

import com.gadidev.momomovies.model.Movies
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("comingsoon")
    suspend fun getComingSoon() : Response<List<Movies>>

}