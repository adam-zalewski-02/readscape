package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EpubAvailabilityDao {
    @Query("SELECT * FROM epub_availability")
    fun getAllEpubAvailabilities(): List<EpubAvailability>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpubAvailability(epubAvailability: EpubAvailability)
}