package com.example.polydeadlines.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Date

@Entity
@Serializable
data class Panel(
    @PrimaryKey val subject: String,
    @ColumnInfo val task: String,
    @ColumnInfo val date: String,
    @ColumnInfo var isComplete: Boolean
)