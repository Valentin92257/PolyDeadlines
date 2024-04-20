package com.example.polydeadlines.View

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp


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
}