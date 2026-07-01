package com.example.final_exam_project.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import com.example.final_exam_project.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Exposes DashboardUiState as StateFlow; computed from repository Flows.
// Full goalProgress/streak computation (via repository.countSince and
// repository.allSessions) lands in Milestone 6.
class DashboardViewModel(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()
}
