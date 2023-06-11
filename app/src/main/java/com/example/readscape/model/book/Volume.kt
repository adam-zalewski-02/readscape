package com.example.readscape.model.book
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable

@Entity(tableName = "volumes")
data class Volume(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "kind") val kind: String,
    @ColumnInfo(name = "etag") val etag: String,
    @ColumnInfo(name = "self_link") val selfLink: String,
    @Embedded val volumeInfo: VolumeInfo,
    @Embedded val saleInfo: SaleInfo,
    @Embedded val accessInfo: AccessInfo
)

