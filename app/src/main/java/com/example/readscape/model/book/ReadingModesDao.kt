package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReadingModesDao {
    @Query("SELECT * FROM reading_modes")
    fun getAllReadingModes(): List<ReadingModes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReadingModes(readingModes: ReadingModes)

}