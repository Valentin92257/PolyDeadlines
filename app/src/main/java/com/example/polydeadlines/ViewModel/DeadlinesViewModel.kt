package com.example.polydeadlines.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polydeadlines.DataBase.AppDatabase
import com.example.polydeadlines.Model.Panel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeadlinesViewModel @Inject constructor( db: AppDatabase) : ViewModel() {
    private val tasks =mutableStateOf(emptyList<Panel>())
    private val dao = db.userDao()
    private var subjects=  mutableStateOf(emptyList<String>())
    private val filter = mutableStateOf("Все")
    private fun loadDeadlines() {
        viewModelScope.launch() {
            tasks.value = dao.getAll()
        }
    }
    init{
        loadDeadlines()
    }

    fun getTasks(): MutableState<List<Panel>> {
        return tasks
    }
    fun update(panel: Panel){
        viewModelScope.launch() {
            dao.update(panel)
        }
    }
    fun reloadSubjects() {
        val tmpSubjects = sortedSetOf<String>()
        tasks.value.forEach{task -> if(!task.isComplete)tmpSubjects.add(task.subject) }
        subjects.value=tmpSubjects.toMutableList() + listOf("Все","Выполненные")

    }

    fun getSubjects() = subjects

    fun getFilter() = filter

}