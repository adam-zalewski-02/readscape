package com.example.readscape.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int
    ): Response<BooksApiResponse>
}
