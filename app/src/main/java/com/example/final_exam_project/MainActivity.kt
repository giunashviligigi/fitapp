package com.example.final_exam_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.final_exam_project.di.ViewModelFactory
import com.example.final_exam_project.navigation.FitTrackNavHost
import com.example.final_exam_project.ui.theme.FitTrackTheme

// Single-Activity Compose entry point. No XML layouts, no findViewById.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val container = (application as FitTrackApplication).container
        val viewModelFactory = ViewModelFactory(container)

        setContent {
            FitTrackTheme {
                FitTrackNavHost(viewModelFactory = viewModelFactory)
            }
        }
    }
}