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
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.polydeadlines.Model.Panel
import com.example.polydeadlines.Model.toTargetDateFormat
import com.example.polydeadlines.ViewModel.DeadlinesViewModel
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth(onDismis: () -> Unit) {
    var mail by remember { mutableStateOf("") }
    BasicAlertDialog(
        onDismissRequest = onDismis,
    ) {
        Column(Modifier.background(Color.White, MaterialTheme.shapes.medium)) {
            Text(
                text = "Введите логин:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                color = Color.Black,
                fontSize = 15.sp,
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
                    Text("Отмена")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    modifier = Modifier
                        .weight(1.0F),
                    onClick = onDismis
                ) {
                    Text("Ок")
                }
            }

        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(isExpanded: MutableState<Boolean>) {
    //var isExpanded = remember { mutableStateOf(false) }
    var chapter by remember { mutableStateOf("111111") }
    ExposedDropdownMenuBox(
        expanded = isExpanded.value,
        onExpandedChange = {isExpanded.value=!isExpanded.value}
    ) {
        TextField(
            readOnly = true,
            value = chapter,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded.value
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
        ) {
            DropdownMenuItem(
                text = { Text("11111") },
                onClick = {
                    chapter = "111111"
                    isExpanded.value = false
                }
            )
            DropdownMenuItem(
                text = { Text("222222") },
                onClick = {
                    chapter = "222222"
                    isExpanded.value = false
                }
            )
        }
    }
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(isExpanded: MutableState<Boolean>, onClick: () -> Unit) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Gray,
            titleContentColor = Color.Cyan,
        ),
        title = {
            //Text("Small Top App Bar")
        },
        actions = {
            //DropDownMenu(isExpanded)
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Log in"
                )
            }
        }
    )
}

var tasks = mutableListOf(
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false),
    Panel("1","math", "Some task", toTargetDateFormat("20240418T210000Z"), false)
)


@Composable
fun Deadlines(viewModel: DeadlinesViewModel = hiltViewModel()) {
    var isExpanded = remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { TopBar(isExpanded) { openDialog = true } },
    ) { innerPadding ->
        DeadLineColumn(modifier = Modifier.padding(innerPadding))
    }

    if (openDialog)
        Auth { openDialog = false }
}


/*
@Composable
fun DeadLineCard(data: DeadLineData) {
    val checkedState = remember { mutableStateOf(data.isActive) }
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))    ) {

        Column {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(text = data.name, style = typography.labelLarge)
                Checkbox(checked = checkedState.value, onCheckedChange = {
                    data.isActive = !data.isActive
                    checkedState.value = it
                })
            }
            Row {
                Text(
                    text = data.date,
                    style = typography.titleSmall,
                    modifier = Modifier.padding(5.dp)
                )

                Text(
                    text = data.time.toString(),
                    style = typography.titleSmall,
                    modifier = Modifier.padding(5.dp)
                )

            }


        }
    }
}*/
