package com.example.polydeadlines.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.polydeadlines.Model.Panel

@Database(entities = [Panel::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): PanelDao


}