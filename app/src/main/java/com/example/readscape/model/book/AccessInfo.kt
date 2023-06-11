package com.example.readscape.model.book

import com.example.readscape.model.PdfAvailability
import kotlinx.serialization.Serializable

@Serializable
data class AccessInfo(
    val country: String,
    val viewability: String,
    val embeddable: Boolean,
    val publicDomain: Boolean,
    val textToSpeechPermission: String,
    val epub: EpubAvailability,
    val pdf: PdfAvailability,
    val webReaderLink: String,
    val accessViewStatus: String,
    val quoteSharingAllowed: Boolean
)