package com.example.polydeadlines.View

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polydeadlines.Model.Panel
import com.example.polydeadlines.Model.toTargetDateFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth(onDismis: ()->Unit) {
    val mail = remember { mutableStateOf("") }
    BasicAlertDialog(
        onDismissRequest =onDismis,
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
                value = mail.value,
                onValueChange = { newText -> mail.value = newText },
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
                    onClick = onDismis) {
                    Text("Отмена")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    modifier = Modifier
                        .weight(1.0F),
                    onClick = onDismis) {
                    Text("Ок")
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onClick: () -> Unit){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Gray,
            titleContentColor = Color.Cyan,
        ),
        title = {
            Text("Small Top App Bar")
        },
        actions = {
            IconButton(onClick = onClick){
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Log in"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Deadlines() {
    val openDialog = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {TopBar({openDialog.value = true})},
    ) {
        innerPadding->
        Text("Content",Modifier.padding(innerPadding))
        var test = Panel("math","asdfkjalsjdfas", toTargetDateFormat("20240418T210000Z"),false)
        DeadLineCard(test)
    }

    if(openDialog.value)
        Auth { openDialog.value = false }
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
