package com.example.readscape.model.book

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "panelization_summary")
data class PanelizationSummary(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "contains_epub_bubbles") val containsEpubBubbles: Boolean,
    @ColumnInfo(name = "contains_image_bubbles") val containsImageBubbles: Boolean
)