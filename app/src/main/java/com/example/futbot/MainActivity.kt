package com.example.futbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.futbot.ui.navigation.NavGraph
import com.example.futbot.ui.theme.FutbotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FutbotTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NavGraph()
                    }
                }
            }
        }
    }
}

