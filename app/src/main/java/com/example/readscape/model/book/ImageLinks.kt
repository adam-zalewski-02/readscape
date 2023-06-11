package com.example.readscape.model.book
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "image_links")
data class ImageLinks(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "small_thumbnail") val smallThumbnail: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String
)