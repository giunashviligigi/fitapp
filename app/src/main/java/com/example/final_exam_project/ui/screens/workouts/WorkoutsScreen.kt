package com.example.final_exam_project.ui.screens.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_exam_project.di.ViewModelFactory

@Composable
fun WorkoutsScreen(
    viewModelFactory: ViewModelFactory,
    editingId: Long? = null,
    modifier: Modifier = Modifier
) {
    val viewModel: WorkoutsViewModel = viewModel(factory = viewModelFactory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    // TODO (Milestone 5): LaunchedEffect(editingId) { viewModel.loadForEdit(editingId) }

    Scaffold(modifier = modifier) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            OutlinedTextField(
                value = uiState.exerciseName,
                onValueChange = { /* TODO (Milestone 5): viewModel.onExerciseNameChange(it) */ },
                label = { Text("Exercise name") }
            )
            OutlinedTextField(
                value = uiState.sets,
                onValueChange = { /* TODO (Milestone 5): viewModel.onSetsChange(it) */ },
                label = { Text("Sets") }
            )
            OutlinedTextField(
                value = uiState.reps,
                onValueChange = { /* TODO (Milestone 5): viewModel.onRepsChange(it) */ },
                label = { Text("Reps") }
            )
            OutlinedTextField(
                value = uiState.weight,
                onValueChange = { /* TODO (Milestone 5): viewModel.onWeightChange(it) */ },
                label = { Text("Weight (kg)") }
            )
            Button(onClick = { /* TODO (Milestone 5): viewModel.save() */ }) {
                Text("Save workout")
            }
        }
    }
}
