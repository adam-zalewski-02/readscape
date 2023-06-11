package com.example.readscape.model

import kotlinx.serialization.Serializable

@Serializable
data class PdfAvailability(
    val isAvailable: Boolean
)
