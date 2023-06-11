package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VolumeDao {
    @Query("SELECT * FROM volumes")
    fun getAllVolumes(): List<Volume>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVolume(volume: Volume)
}