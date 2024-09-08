package com.example.polydeadlines.View

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.polydeadlines.R
import com.example.polydeadlines.ViewModel.DeadlinesViewModel


lateinit var viewModel: DeadlinesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth(onDismis: () -> Unit,onAccept: (email:String) -> Unit) {
    var textLabel by remember { mutableStateOf("") }
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
                text = "Введите корпоративную почту:",
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
                label = { Text(textLabel)},
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
                    onClick = {
                        if(mail.matches(Regex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$")))
                            onAccept(mail)
                        else {
                            textLabel = "Неверный формат"
                        }
                    }
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
    val context = LocalContext.current
    viewModel = createdViewModel
    val tasks = viewModel.getTasks()

    var isTasksEmpty by remember { mutableStateOf(false) }
    var emailButtonClicked by remember { mutableStateOf(false) }
    isTasksEmpty = tasks.value.isEmpty()

    Scaffold(
        topBar = { TopBar { emailButtonClicked = true } },
    ) { innerPadding ->
        DeadLineColumn(modifier = Modifier.padding(innerPadding))
    }

    if (isTasksEmpty||emailButtonClicked)
        Auth(
            onDismis = { emailButtonClicked = false }
        ) {
            viewModel.loadDeadlinesEmail(it) { Toast.makeText(context, "Проблемы с доступом в Интернет", Toast.LENGTH_LONG).show() }
            emailButtonClicked = false
        }
}


