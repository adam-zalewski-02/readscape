package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.readscape.model.PdfAvailability

@Dao
interface PdfAvailabilityDao {
    @Query("SELECT * FROM pdf_availability")
    fun getAllPdfAvailabilities(): List<PdfAvailability>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPdfAvailability(pdfAvailability: PdfAvailability)
}