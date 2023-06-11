package com.example.readscape.network

import com.example.readscape.model.book.Volume
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://www.googleapis.com/books/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BookService {
    @GET("volumes?q=search+terms")
    suspend fun getAllVolumes(): List<Volume>

    @GET("volumes/{volumeId}")
    suspend fun getVolumeById(@Path("volumeId") volumeId:String) : Volume
}

object BookApi {
    val retrofitService : BookService by lazy {
        retrofit.create(BookService::class.java)
    }
}
