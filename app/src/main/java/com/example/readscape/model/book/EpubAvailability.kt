package com.example.readscape.model.book

import kotlinx.serialization.Serializable

@Serializable
data class EpubAvailability(
    val isAvailable: Boolean
)
