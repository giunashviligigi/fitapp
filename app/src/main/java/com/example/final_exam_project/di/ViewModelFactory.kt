package com.example.final_exam_project.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_exam_project.ui.screens.dashboard.DashboardViewModel
import com.example.final_exam_project.ui.screens.history.HistoryViewModel
import com.example.final_exam_project.ui.screens.workouts.WorkoutsViewModel

// Builds ViewModels with their repository dependency, sourced from the AppContainer.
class ViewModelFactory(private val container: AppContainer) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(DashboardViewModel::class.java) ->
            DashboardViewModel(container.workoutRepository) as T

        modelClass.isAssignableFrom(WorkoutsViewModel::class.java) ->
            WorkoutsViewModel(container.workoutRepository) as T

        modelClass.isAssignableFrom(HistoryViewModel::class.java) ->
            HistoryViewModel(container.workoutRepository) as T

        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
