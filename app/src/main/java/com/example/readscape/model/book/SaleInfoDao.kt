package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SaleInfoDao {
    @Query("SELECT * FROM sale_info")
    fun getAllSaleInfos(): List<SaleInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSaleInfo(saleInfo: SaleInfo)

}
