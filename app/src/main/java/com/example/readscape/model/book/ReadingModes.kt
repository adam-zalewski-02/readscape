package com.example.readscape.model.book

import kotlinx.serialization.Serializable

@Serializable
data class ReadingModes(
    val text: Boolean,
    val image: Boolean
)
