package com.example.polydeadlines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.polydeadlines.View.TopBar
import com.example.polydeadlines.Model.Panel
import com.example.polydeadlines.Model.toTargetDateFormat
import com.example.polydeadlines.View.DeadLineCard
import com.example.polydeadlines.ui.theme.PolyDeadlinesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PolyDeadlinesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopBar()
                }
            }

            var test = Panel("math","asdfkjalsjdfas", toTargetDateFormat("20240418T210000Z"),false)
            DeadLineCard(test)
        }
    }
}
