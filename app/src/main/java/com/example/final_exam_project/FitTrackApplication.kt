package com.example.final_exam_project

import android.app.Application
import com.example.final_exam_project.di.AppContainer
import com.example.final_exam_project.di.DefaultAppContainer

// Owns the single AppContainer instance for the app's lifetime (manual DI root).
class FitTrackApplication : Application() {

    val container: AppContainer by lazy { DefaultAppContainer(this) }
}
