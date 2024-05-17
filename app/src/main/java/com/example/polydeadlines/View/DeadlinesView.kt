package com.example.polydeadlines.View

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.polydeadlines.R
import com.example.polydeadlines.ViewModel.DeadlinesViewModel
import java.text.SimpleDateFormat
import java.util.Locale


lateinit var viewModel: DeadlinesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth(onDismis: () -> Unit,onAccept: () -> Unit) {
    var mail by remember { mutableStateOf("") }
    BasicAlertDialog(
        onDismissRequest = onDismis,
    ) {
        Column(
            Modifier.background(
                MaterialTheme.colorScheme.surfaceVariant,
                MaterialTheme.shapes.medium
            )
        ) {
            Text(
                text = "Введите логин:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight(1000),
                textAlign = TextAlign.Center
            )
            TextField(
                value = mail,
                onValueChange = { newText -> mail = newText },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            )
            Row(
                modifier = Modifier.padding(5.dp)
            ) {
                Button(
                    modifier = Modifier
                        .weight(1.0F),
                    onClick = onDismis
                ) {
                    Text(
                        "Отмена",
                        fontWeight = FontWeight(1000)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    modifier = Modifier
                        .weight(1.0F),
                    onClick = onAccept
                ) {
                    Text(
                        "Ок",
                        fontWeight = FontWeight(1000)
                    )
                }
            }
        }
    }
}

@Composable
fun DropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val subjects = viewModel.getSubjects().value
    val icon = if (expanded) {
        Icons.Default.KeyboardArrowUp
    } else {
        Icons.Default.KeyboardArrowDown
    }
    IconButton(onClick = { expanded = !expanded }) {
        Icon(
            imageVector = icon,
            contentDescription = "More"
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        subjects.forEach { label ->
            DropdownMenuItem(text = { Text(text = label) }, onClick = {
                viewModel.getFilter().value = label
                expanded = false
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onClick: () -> Unit) {
    val topBatText = if (viewModel.getFilter().value == stringResource(R.string.all)) {
        stringResource(R.string.app_name)
    } else {
        viewModel.getFilter().value
    }
    TopAppBar(
        title = {
            Text(topBatText)
        }, navigationIcon = {
            DropDownMenu()

        },
        actions = {

            IconButton(
                onClick = onClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Log in",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Deadlines(createdViewModel: DeadlinesViewModel = hiltViewModel()) {
    viewModel = createdViewModel
    // 3ч =10800
    /*val a = System.currentTimeMillis()//текущее время в секундах в часовом поясе +3
    val targetFormat = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.US)
    val f= targetFormat.format(a)*/
    //for example on physical devices without moodle
    /*viewModel.insert(com.example.polydeadlines.Model.Panel("1", "Английский", "1Some task", "2024.04.18 21:00", false))
    viewModel.insert(com.example.polydeadlines.Model.Panel("2", "Английский", "2Some task", "2024.04.18 21:00", false))
    viewModel.insert(com.example.polydeadlines.Model.Panel("3", "Английский", "Какое то задание непонятно зачем не нужно это делать", "2024.04.18 21:00", false))
    viewModel.insert(com.example.polydeadlines.Model.Panel("4", "Высшая математика Солева Антонина", "Какое то задание непонятно зачем не нужно это делать", "2024.04.18 21:00", false))
    viewModel.insert(com.example.polydeadlines.Model.Panel("5", "Высшая математика Солева Антонина", "2Some task", "2024.04.18 21:00", false))
    viewModel.insert(com.example.polydeadlines.Model.Panel("6", "География", "1Some task", "2024.04.18 21:00", false))
*/
    var openDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { TopBar { openDialog = true } },
    ) { innerPadding ->
        DeadLineColumn(modifier = Modifier.padding(innerPadding))
    }

    if (openDialog)
        Auth (onDismis={ openDialog = false },
            onAccept= {
                openDialog = false
                viewModel.insert(
                    com.example.polydeadlines.Model.Panel(
                        "8",
                        "Английский",
                        "8Some task",
                        1715708271000,
                        false
                    )
                )
                viewModel.insert(
                    com.example.polydeadlines.Model.Panel(
                        "9",
                        "География",
                        "9Some task",
                        1715708271000,
                        false
                    )
                )
            })
}


