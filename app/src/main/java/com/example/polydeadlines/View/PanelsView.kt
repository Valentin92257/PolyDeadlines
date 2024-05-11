package com.example.polydeadlines.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polydeadlines.Model.Panel


@Composable
fun DeadLineColumn(modifier: Modifier) {
    val completedTasks = remember { mutableStateListOf<Panel>() }
    LazyColumn(modifier) {
        itemsIndexed(
            items = tasks,
            itemContent = { _, item ->
                AnimatedVisibility(
                    visible = !completedTasks.contains(item)
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
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1.0F)
            ) {

                Text(
                    text = item.subject,
                    style = typography.labelLarge,
                    modifier = Modifier.padding(7.dp),
                    fontWeight = FontWeight(300),
                    fontSize = 5.sp
                )
                Text(
                    text = item.task,
                    style = typography.titleSmall,
                    modifier = Modifier.padding(7.dp)
                )

            }
            Column(
                modifier = Modifier.weight(0.3F)
            ) {
                Checkbox(
                    checked = checkedState.value,
                    modifier = Modifier
                        .padding(7.dp),
                    onCheckedChange = {
                        item.isComplete = !item.isComplete
                        completedTasks.add(item)
                        checkedState.value = it
                    })
                Text(
                    text = item.date,
                    style = typography.titleSmall,
                    modifier = Modifier.padding(7.dp)
                )
            }
        }
    }
}