package com.example.final_exam_project.ui.screens.workouts

import androidx.lifecycle.ViewModel
import com.example.final_exam_project.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Exposes WorkoutFormUiState as StateFlow; field-update handlers, validation,
// save()/loadForEdit(id) land in Milestone 5.
class WorkoutsViewModel(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WorkoutFormUiState())
    val uiState: StateFlow<WorkoutFormUiState> = _uiState.asStateFlow()
}
