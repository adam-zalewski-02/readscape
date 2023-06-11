package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VolumeInfoDao {
    @Query("SELECT * FROM volume_info")
    fun getAllVolumeInfos(): List<VolumeInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVolumeInfo(volumeInfo: VolumeInfo)

}