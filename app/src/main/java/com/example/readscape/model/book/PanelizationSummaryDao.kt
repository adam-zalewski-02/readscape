package com.example.readscape.model.book
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PanelizationSummaryDao {
    @Query("SELECT * FROM panelization_summary")
    fun getAllPanelizationSummaries(): List<PanelizationSummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPanelizationSummary(panelizationSummary: PanelizationSummary)

}