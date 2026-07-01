package com.example.final_exam_project.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.final_exam_project.data.local.WorkoutSession
import com.example.final_exam_project.util.DateFormatter

// A single row in the History LazyColumn: exercise/sets/reps/weight/timestamp,
// tap to edit (navigates to Workouts screen pre-filled), icon to delete.
@Composable
fun WorkoutListItem(
    session: WorkoutSession,
    onClick: (WorkoutSession) -> Unit,
    onDelete: (WorkoutSession) -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier.clickable { onClick(session) },
        headlineContent = { Text(session.exerciseName) },
        supportingContent = {
            Text("${session.sets} x ${session.reps} @ ${session.weight}kg  •  ${DateFormatter.format(session.timestamp)}")
        },
        trailingContent = {
            IconButton(onClick = { onDelete(session) }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete workout")
            }
        }
    )
}
