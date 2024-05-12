package com.example.polydeadlines.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Entity
@Serializable
data class Panel(
    @PrimaryKey @SerialName("UID") val uid : String,
    @SerialName("SUBJECT") @ColumnInfo  val subject: String,
    @SerialName("TASK") @ColumnInfo val task: String,
    @SerialName("DATE") @ColumnInfo val date: Long,
    @ColumnInfo var isComplete: Boolean = false
)