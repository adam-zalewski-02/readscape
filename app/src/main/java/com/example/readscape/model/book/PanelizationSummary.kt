package com.example.readscape.model.book

import kotlinx.serialization.Serializable

@Serializable
data class PanelizationSummary(
    val containsEpubBubbles: Boolean,
    val containsImageBubbles: Boolean
)
