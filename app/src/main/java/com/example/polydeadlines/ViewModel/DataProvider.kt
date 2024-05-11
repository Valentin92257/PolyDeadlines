package com.example.polydeadlines.ViewModel

import com.example.polydeadlines.Model.Panel
import kotlinx.serialization.json.Json.Default.decodeFromString
class DataProvider {

    fun getDeadlineFromJSON(json: String) : List<Panel> = decodeFromString<List<Panel>>(json)
    fun filteredDeadlineList(deadlineList: List<Panel>, subject: String): List<Panel> =
        deadlineList.filter { it.subject != subject }
}