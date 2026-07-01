package com.example.final_exam_project.ui.screens.dashboard

// UI state for the Dashboard: goal-ring progress plus quick summary stats.
data class DashboardUiState(
    val goalProgress: Float = 0f,
    val sessionsThisWeek: Int = 0,
    val weeklyGoalTarget: Int = 5,
    val totalSessions: Int = 0,
    val currentStreak: Int = 0
)
