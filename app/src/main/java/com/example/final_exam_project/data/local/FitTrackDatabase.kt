package com.example.final_exam_project.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WorkoutSession::class], version = 1, exportSchema = false)
abstract class FitTrackDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile private var INSTANCE: FitTrackDatabase? = null

        fun getInstance(context: Context): FitTrackDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FitTrackDatabase::class.java,
                    "fittrack.db"
                ).build().also { INSTANCE = it }
            }
    }
}
