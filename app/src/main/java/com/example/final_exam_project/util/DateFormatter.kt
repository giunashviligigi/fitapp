package com.example.final_exam_project.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Formats a WorkoutSession's epoch-millis timestamp for display in the UI.
object DateFormatter {

    private val displayFormat = SimpleDateFormat("MMM d, yyyy · HH:mm", Locale.getDefault())

    fun format(epochMillis: Long): String = displayFormat.format(Date(epochMillis))
}
