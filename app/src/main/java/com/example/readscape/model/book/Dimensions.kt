package com.example.readscape.model.book
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "dimensions")
data class Dimensions(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "height") val height: String,
    @ColumnInfo(name = "width") val width: String,
    @ColumnInfo(name = "thickness") val thickness: String
)