package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageLinksDao {
    @Query("SELECT * FROM image_links")
    fun getAllImageLinks(): List<ImageLinks>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImageLinks(imageLinks: ImageLinks)

}