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
    val uid : String,
    @SerialName("CATEGORIES") @ColumnInfo  val subject: String,
    @SerialName("SUMMARY") @ColumnInfo val task: String,
    @SerialName("DTEND") @ColumnInfo val date: String,
    @ColumnInfo var isComplete: Boolean = false
){
    @SerialName("UID") @PrimaryKey var id = Integer.parseInt(uid.filter { it.isDigit() })
}