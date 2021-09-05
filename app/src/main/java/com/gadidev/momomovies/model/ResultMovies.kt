package com.gadidev.momomovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class ResultMovies (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

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

