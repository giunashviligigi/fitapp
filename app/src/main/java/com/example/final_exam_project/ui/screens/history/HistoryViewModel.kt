package com.example.final_exam_project.ui.screens.history

import androidx.lifecycle.ViewModel
import com.example.final_exam_project.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Exposes HistoryUiState as StateFlow. Collecting repository.allSessions into
// this state, plus delete(session), land in Milestone 5.
class HistoryViewModel(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()
}
