package com.example.readscape.model.book
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.readscape.model.PdfAvailability
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "access_info")
data class AccessInfo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "viewability") val viewability: String,
    @ColumnInfo(name = "embeddable") val embeddable: Boolean,
    @ColumnInfo(name = "public_domain") val publicDomain: Boolean,
    @ColumnInfo(name = "text_to_speech_permission") val textToSpeechPermission: String,
    @Embedded(prefix = "epub_") val epub: EpubAvailability,
    @Embedded(prefix = "pdf_") val pdf: PdfAvailability,
    @ColumnInfo(name = "web_reader_link") val webReaderLink: String,
    @ColumnInfo(name = "access_view_status") val accessViewStatus: String,
    @ColumnInfo(name = "quote_sharing_allowed") val quoteSharingAllowed: Boolean
)