package com.example.readscape.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Book(
    val title: String,
    val author: String
)