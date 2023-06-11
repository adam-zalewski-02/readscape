package com.example.readscape.model.book

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse (
    val kind: String,
    val totalItems: Int,
    val items: List<Volume>
)