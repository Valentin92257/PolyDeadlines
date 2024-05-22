package com.example.polydeadlines.retrofit

import com.example.polydeadlines.Model.Panel
import retrofit2.http.GET
import retrofit2.http.Query

interface MoodleApi {
    @GET("task_data")
    suspend fun getDeadlines(@Query("email") email: String): List<Panel>
}