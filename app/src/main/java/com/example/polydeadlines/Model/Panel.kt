package com.example.polydeadlines.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Panel(
    @PrimaryKey  val UID : String,
    @ColumnInfo  val SUBJECT: String,
    @ColumnInfo val TASK: String,
    @ColumnInfo val DATE: Long,
    @ColumnInfo var isComplete: Boolean = false
){
    override fun equals(other: Any?): Boolean {
        if(other is Panel)
            return this.UID == other.UID
        return false
    }
}