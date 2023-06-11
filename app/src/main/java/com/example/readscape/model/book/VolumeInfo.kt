package com.example.readscape.model.book

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Serializable
data class VolumeInfo(
    val title: String,
    val subtitle: String?,
    val authors: List<String>?,
    val publishedDate: String?,
    val description: String?,
    val industryIdentifiers: List<IndustryIdentifier>?,
    val readingModes: ReadingModes?,
    val pageCount: Int?,
    val printType: String?,
    val maturityRating: String?,
    val allowAnonLogging: Boolean?,
    val contentVersion: String?,
    val panelizationSummary: PanelizationSummary?,
    val imageLinks: ImageLinks?,
    val language: String?,
    val previewLink: String?,
    val infoLink: String?,
    val canonicalVolumeLink: String?
)
