package com.example.polydeadlines.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polydeadlines.DataBase.AppDatabase
import com.example.polydeadlines.Model.Panel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DeadlinesViewModel @Inject constructor( db: AppDatabase) : ViewModel() {
    private val tasks =mutableStateOf(emptyList<Panel>())
    private val dao = db.userDao()
    private var subjects=  mutableStateOf(emptyList<String>())
    private val filter = mutableStateOf("Все")
    init{
        viewModelScope.launch() {
            tasks.value = dao.getAll()
        }
    }

    fun getTasks(): MutableState<List<Panel>> {
        return tasks
    }

    fun insert(panel: Panel){
        viewModelScope.launch() {
            dao.insert(panel)
            tasks.value = dao.getAll()
        }
    }

    fun loadDeadlinesEmail(email : String){
        viewModelScope.launch() {
            //API запрос и сравнение с имеющимся списком (добавлять только те которых нет в tasks)
            //dao.insert()
            tasks.value = dao.getAll()
        }
    }

    fun update(panel: Panel){
        viewModelScope.launch() {
            dao.update(panel)
        }
    }
    fun reloadSubjects() {
        val tmpSubjects = sortedSetOf<String>()
        tasks.value.forEach{task -> if(!task.isComplete)tmpSubjects.add(task.subject) }
        subjects.value=tmpSubjects.toMutableList() + listOf("Прошедшие","Выполненные","Все")

    }

    fun convertDate(mills: Long) =
        SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US).format(mills)
    fun getCurrentDate()=System.currentTimeMillis()
    fun getSubjects() = subjects

    fun getFilter() = filter

}