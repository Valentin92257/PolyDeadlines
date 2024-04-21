package com.example.polydeadlines.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.polydeadlines.Model.Panel
import kotlinx.coroutines.flow.Flow

@Dao
interface PanelDao {
    @Query("SELECT * FROM panel")
    fun getAll(): List<Panel>

    @Insert
    suspend fun insert(panel: Panel)

    @Delete
    suspend fun delete(panel: Panel)

    @Update
    suspend fun update(panel: Panel)
}