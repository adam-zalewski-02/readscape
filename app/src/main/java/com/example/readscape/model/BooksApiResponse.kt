package com.example.readscape.model

import com.google.gson.annotations.SerializedName

data class BooksApiResponse(
    @SerializedName("items") val books: List<Book>
)
