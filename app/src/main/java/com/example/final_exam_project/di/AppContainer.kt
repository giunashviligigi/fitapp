package com.example.final_exam_project.di

import android.content.Context
import com.example.final_exam_project.data.local.FitTrackDatabase
import com.example.final_exam_project.data.repository.WorkoutRepository

// Manual DI container: single source of truth for app-wide dependencies (no Hilt).
interface AppContainer {
    val workoutRepository: WorkoutRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    private val database: FitTrackDatabase by lazy {
        FitTrackDatabase.getInstance(context)
    }

    override val workoutRepository: WorkoutRepository by lazy {
        WorkoutRepository(database.workoutDao())
    }
}
