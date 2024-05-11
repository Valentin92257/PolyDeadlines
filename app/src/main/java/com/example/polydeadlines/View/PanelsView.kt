package com.example.polydeadlines.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polydeadlines.Model.Panel
import com.example.polydeadlines.ui.theme.Green40
import com.example.polydeadlines.ui.theme.Green80
import com.example.polydeadlines.ui.theme.Grey40


@Composable
fun DeadLineColumn(modifier: Modifier) {
    val completedTasks = remember { mutableStateListOf<Panel>() }
    LazyColumn(modifier) {
        itemsIndexed(
            items = tasks,
            itemContent = { _, item ->
                AnimatedVisibility(
                    visible = !completedTasks.contains(item),
                    //exit = fadeOut()      TODO
                ) {
                    DeadLineCard(item, completedTasks)
                }
            }
        )
    }
}


@Composable
fun DeadLineCard(item: Panel, completedTasks: SnapshotStateList<Panel>) {
    val checkedState = remember { mutableStateOf(item.isComplete) }
    Card(

        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 1.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.8f).padding(10.dp),
            )
            {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(
                        modifier = Modifier.width(3.dp)
                            .height(25.dp)
                            .background(Green40)
                    )

                    Text(
                        text = item.date,
                        style = typography.titleSmall,
                        modifier = Modifier.padding(start=5.dp),
                        fontSize = 20.sp
                    )
                }

                Text(
                    text = item.subject,
                    style = typography.labelLarge,
                    fontWeight = FontWeight(1000),
                    fontSize = 30.sp
                )

                Text(
                    text = item.task,
                    style = typography.titleSmall,
                )

            }


            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = Green80,
                    uncheckedColor = Green40,
                    checkmarkColor = Color.White,
                    disabledCheckedColor = Green40,
                    disabledUncheckedColor = Grey40,
                    disabledIndeterminateColor = Grey40
                ),
                checked = checkedState.value,
                modifier = Modifier
                    .padding(7.dp)
                    .weight(0.2F),
                onCheckedChange = {
                    item.isComplete = !item.isComplete
                    completedTasks.add(item)
                    checkedState.value = it
                },

                )
        }
    }
}