package com.example.readscape.model

import com.example.readscape.model.book.Volume
import com.example.readscape.network.BookService

interface BookRepository {
    suspend fun getAllVolumes(): List<Volume>
    suspend fun getVolumeById(volumeId:String): Volume
}

class DefaultBookRepository(private val bookApiService: BookService) : BookRepository {
    override suspend fun getAllVolumes(): List<Volume> {
        val response = bookApiService.getAllVolumes()
        return response.items
    }

    override suspend fun getVolumeById(volumeId:String) : Volume {
        return bookApiService.getVolumeById(volumeId).items[0]
    }
}