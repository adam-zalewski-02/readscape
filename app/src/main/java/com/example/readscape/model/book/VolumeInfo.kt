package com.example.readscape.model.book
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "volume_info")
data class VolumeInfo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "subtitle") val subtitle: String?,
    @ColumnInfo(name = "authors") val authors: List<String>,
    @ColumnInfo(name = "publisher") val publisher: String,
    @ColumnInfo(name = "published_date") val publishedDate: String,
    @ColumnInfo(name = "description") val description: String,
    @Embedded(prefix = "industry_identifier_") val industryIdentifier: IndustryIdentifier,
    @Embedded(prefix = "reading_modes_") val readingModes: ReadingModes,
    @ColumnInfo(name = "page_count") val pageCount: Int,
    @ColumnInfo(name = "printed_page_count") val printedPageCount: Int,
    @Embedded val dimensions: Dimensions,
    @ColumnInfo(name = "print_type") val printType: String,
    @ColumnInfo(name = "maturity_rating") val maturityRating: String,
    @ColumnInfo(name = "allow_anon_logging") val allowAnonLogging: Boolean,
    @ColumnInfo(name = "content_version") val contentVersion: String,
    @Embedded(prefix = "panelization_summary_") val panelizationSummary: PanelizationSummary,
    @Embedded(prefix = "image_links_") val imageLinks: ImageLinks,
    @ColumnInfo(name = "language") val language: String,
    @ColumnInfo(name = "preview_link") val previewLink: String,
    @ColumnInfo(name = "info_link") val infoLink: String,
    @ColumnInfo(name = "canonical_volume_link") val canonicalVolumeLink: String
)