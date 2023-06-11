package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IndustryIdentifierDao {
    @Query("SELECT * FROM industry_identifiers")
    fun getAllIndustryIdentifiers(): List<IndustryIdentifier>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIndustryIdentifier(industryIdentifier: IndustryIdentifier)

}