package com.example.polydeadlines.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.polydeadlines.Model.Panel

@Dao
interface PanelDao {
    @Query("SELECT * FROM panel")
    fun getAll(): List<Panel>

    @Insert
    fun insertAll(vararg panels: Panel)

    @Delete
    fun delete(panel: Panel)
}