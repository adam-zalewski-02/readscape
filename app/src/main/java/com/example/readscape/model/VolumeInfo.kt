package com.example.readscape.model

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("title") val title: String,
    @SerializedName("authors") val authors: List<String>,
    @SerializedName("description") val description: String,
    // Add other fields as per your requirements
)