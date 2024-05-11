package com.example.polydeadlines.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polydeadlines.DataBase.AppDatabase
import com.example.polydeadlines.Model.Panel
import com.example.polydeadlines.Model.toTargetDateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeadlinesViewModel @Inject constructor( private val db: AppDatabase) : ViewModel() {
    private val tasks =mutableStateOf(emptyList<Panel>())
    private val dao = db.userDao()
    var subjects: HashSet<String> = emptySet<String>().toHashSet()
    val filter = mutableStateOf("")
    private fun loadDeadlines() {
        viewModelScope.launch() {
            tasks.value = dao.getAll()
        }
            tasks.value.forEach{
                    panel -> subjects.add(panel.subject)
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
        tasks.value.forEach{
                panel -> subjects.add(panel.subject)
        }
    }

}