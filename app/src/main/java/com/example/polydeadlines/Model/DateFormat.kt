package com.example.polydeadlines.Model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun toTargetDateFormat(dateString: String): String {
    val currentFormat = SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'")
    val date = currentFormat.parse(dateString)
    val targetFormat = SimpleDateFormat("'До' yyyy.MM.dd HH:mm")
    return targetFormat.format(date!!)
}