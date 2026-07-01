package com.example.final_exam_project.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room entity for a single logged workout (one exercise, one set entry).
@Entity(tableName = "workout_sessions")
data class WorkoutSession(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val exerciseName: String,
    val sets: Int,
    val reps: Int,
    val weight: Double,
    val timestamp: Long = System.currentTimeMillis()
)
