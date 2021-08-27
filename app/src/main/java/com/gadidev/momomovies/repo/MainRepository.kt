package com.gadidev.momomovies.repo

import com.gadidev.momomovies.services.ApiService

class MainRepository constructor(private val retrofitService: ApiService) {

    suspend fun getComingSoon() = retrofitService.getComingSoon()

}