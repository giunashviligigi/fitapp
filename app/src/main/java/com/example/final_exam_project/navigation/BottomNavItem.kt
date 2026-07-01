package com.example.final_exam_project.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : BottomNavItem("dashboard", "Dashboard", Icons.Filled.Home)
    object Workouts : BottomNavItem("workouts", "Workouts", Icons.Filled.FitnessCenter)
    object History : BottomNavItem("history", "History", Icons.Filled.History)

    companion object {
        val items = listOf(Dashboard, Workouts, History)
    }
}
