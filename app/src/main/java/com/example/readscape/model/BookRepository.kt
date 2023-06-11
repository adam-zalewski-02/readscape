package com.example.readscape.model

import com.example.readscape.model.book.Volume
import com.example.readscape.network.BookService

interface BookRepository {
    suspend fun getBooks(): List<Volume>
    suspend fun getBookById(volumeId:String): Volume
}

class DefaultBookRepository(private val bookApiService: BookService) : BookRepository {
    override suspend fun getBooks(): List<Volume> {
        return bookApiService.getAllVolumes()
    }

    override suspend fun getBookById(volumeId:String) : Volume {
        return bookApiService.getVolumeById(volumeId)
    }
}