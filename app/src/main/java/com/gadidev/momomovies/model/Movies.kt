package com.gadidev.momomovies.model
import com.google.gson.annotations.SerializedName


data class Movies (
    @field:SerializedName("result")
    val result: List<ResultMovies>,
    @field:SerializedName("page")
    val page: Int
)

