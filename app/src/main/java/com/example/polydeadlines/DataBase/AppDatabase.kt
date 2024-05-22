package com.example.polydeadlines.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.polydeadlines.Model.Panel

@Database(entities = [Panel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): PanelDao
}