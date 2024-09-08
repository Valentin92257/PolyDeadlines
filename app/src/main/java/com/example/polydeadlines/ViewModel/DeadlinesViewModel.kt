package com.example.polydeadlines.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polydeadlines.DataBase.AppDatabase
import com.example.polydeadlines.Model.Panel
import com.example.polydeadlines.retrofit.MoodleApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DeadlinesViewModel @Inject constructor( db: AppDatabase) : ViewModel() {
    private val tasks = mutableStateOf(emptyList<Panel>())
    private val dao = db.userDao()
    private var subjects = mutableStateOf(emptyList<String>())
    private val filter = mutableStateOf("Все")

    init {
        viewModelScope.launch() {
            tasks.value = dao.getAll()
        }
    }


    fun getTasks(): MutableState<List<Panel>> {
        return tasks
    }

    fun loadDeadlinesEmail(email: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://smuzlmaker.github.io/polyTest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val moodleApi = retrofit.create(MoodleApi::class.java)
            val newElements = moodleApi.getDeadlines(email)


            withContext(viewModelScope.coroutineContext) {
                newElements.forEach {
                    if (!tasks.value.contains(it))
                        dao.insert(it)
                }
                tasks.value = dao.getAll()
            }
        }
    }


    fun update(panel: Panel) {
        viewModelScope.launch() {
            dao.update(panel)
        }
    }

    fun reloadSubjects() {
        val tmpSubjects = sortedSetOf<String>()
        tasks.value.forEach { task -> if (!task.isComplete) tmpSubjects.add(task.SUBJECT) }
        subjects.value = tmpSubjects.toMutableList() + listOf("Прошедшие", "Выполненные", "Все")

    }

    fun convertDate(mills: Long): String =
        SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US).format(mills)

    fun getCurrentDate() = System.currentTimeMillis()
    fun getSubjects() = subjects

    fun getFilter() = filter

}