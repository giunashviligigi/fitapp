package com.example.final_exam_project.ui.screens.history

import com.example.final_exam_project.data.local.WorkoutSession

// UI state for the History screen's LazyColumn.
data class HistoryUiState(
    val sessions: List<WorkoutSession> = emptyList(),
    val isLoading: Boolean = true
)
