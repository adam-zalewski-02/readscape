package com.example.readscape.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo
)