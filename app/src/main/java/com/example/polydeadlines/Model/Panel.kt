package com.example.polydeadlines.Model

import kotlinx.serialization.Serializable
import java.util.Date


@Serializable
data class Panel(
    val subject: String,
    val task: String,
    val date: Date,
    var isComplete: Boolean
)