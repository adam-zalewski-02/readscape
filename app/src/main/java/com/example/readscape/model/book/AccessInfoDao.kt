package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccessInfoDao {
    @Query("SELECT * FROM access_info")
    fun getAllAccessInfos(): List<AccessInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccessInfo(accessInfo: AccessInfo)

}