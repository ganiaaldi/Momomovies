package com.gadidev.momomovies.model

import com.google.gson.annotations.SerializedName

data class Movies (
    @field:SerializedName("result")
    val result: List<ResultMovies>,
)

data class ResultMovies (
    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("thumbnail")
    val thumbnail: String,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("duration")
    val duration: String,

    @field:SerializedName("quality")
    val quality: String,

    @field:SerializedName("trailer")
    val trailer: String,

    @field:SerializedName("genre")
    val genre: List<String>
)

