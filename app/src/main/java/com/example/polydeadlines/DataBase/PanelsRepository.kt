package com.example.polydeadlines.DataBase
import com.example.polydeadlines.Model.Panel
import kotlinx.coroutines.flow.Flow

interface PanelsRepository {
    fun getAllPanels(): List<Panel>

    /**
     * Insert item in the data source
     */
    suspend fun insertPanel(panel: Panel)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(panel: Panel)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(panel: Panel)
}