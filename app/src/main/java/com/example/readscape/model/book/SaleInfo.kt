package com.example.readscape.model.book
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "sale_info")
data class SaleInfo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "saleability") val saleability: String,
    @ColumnInfo(name = "is_ebook") val isEbook: Boolean
)