package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DimensionsDao {
    @Query("SELECT * FROM dimensions")
    fun getAllDimensions(): List<Dimensions>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDimensions(dimensions: Dimensions)

}