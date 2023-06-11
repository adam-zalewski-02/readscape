package com.example.readscape.model
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "pdf_availability")
data class PdfAvailability(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "is_available") val isAvailable: Boolean
)