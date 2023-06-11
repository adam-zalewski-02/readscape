package com.example.readscape.model.book
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "reading_modes")
data class ReadingModes(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "text") val text: Boolean,
    @ColumnInfo(name = "image") val image: Boolean
)