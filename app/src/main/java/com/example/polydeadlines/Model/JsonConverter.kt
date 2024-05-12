package com.example.polydeadlines.Model

import kotlinx.serialization.json.Json.Default.decodeFromString
class JsonConverter {

    fun getDeadlineFromJSON(json: String) : List<Panel> = decodeFromString<List<Panel>>(json)
    fun filteredDeadlineList(deadlineList: List<Panel>, subject: String): List<Panel> =
        deadlineList.filter { it.subject != subject }
}