package com.example.polydeadlines.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polydeadlines.DataBase.AppDatabase
import com.example.polydeadlines.Model.Panel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeadlinesViewModel @Inject constructor( private val repository: AppDatabase) : ViewModel() {
    private val _panels = mutableStateOf<List<Panel>>(emptyList())
    val tasks: State<List<Panel>> = _panels

    init{
        loadDeadlines()
    }
    private fun loadDeadlines() {
        viewModelScope.launch() {
            _panels.value = repository.userDao().getAll()
        }
    }
}