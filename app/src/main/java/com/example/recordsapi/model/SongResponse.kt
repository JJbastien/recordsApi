package com.example.recordsapi.model


import com.google.gson.annotations.SerializedName

data class SongResponse(
    @SerializedName("resultCount")
    val resultCount: Int?,
    @SerializedName("results")
    val songs: List<Songs>
)