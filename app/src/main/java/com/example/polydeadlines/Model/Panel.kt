package com.example.polydeadlines.Model

import java.util.Date

data class Panel(
    val subject: String,
    val task: String,
    val date: String,
    var isComplete: Boolean = false
)