package com.example.final_exam_project.ui.screens.workouts

// UI state for the Workouts entry form. Numeric fields are kept as text while
// being edited; validation errors surface inline (Milestone 5).
data class WorkoutFormUiState(
    val editingId: Long? = null,
    val exerciseName: String = "",
    val sets: String = "",
    val reps: String = "",
    val weight: String = "",
    val exerciseNameError: String? = null,
    val setsError: String? = null,
    val repsError: String? = null,
    val weightError: String? = null,
    val isSaved: Boolean = false
)
