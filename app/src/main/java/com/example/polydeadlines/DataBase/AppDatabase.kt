package com.example.polydeadlines.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.polydeadlines.Model.Panel

@Database(entities = [Panel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): PanelDao

    companion object{
        @Volatile
    private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "panel_database")
                    .build()
                    .also { Instance = it }
            }
        }

    }
}