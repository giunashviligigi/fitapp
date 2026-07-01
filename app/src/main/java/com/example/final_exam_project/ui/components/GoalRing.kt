package com.example.final_exam_project.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * The "wow" feature: an animated daily/weekly goal ring drawn from scratch
 * with the Canvas API (drawArc for track + progress, animateFloatAsState for
 * the fill animation). Full drawArc/animateFloatAsState implementation lands
 * in Milestone 6 — this stub only fixes the composable's public shape.
 */
@Composable
fun GoalRing(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(160.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(160.dp)) {
            // TODO (Milestone 6): drawArc background track + animated progress arc
        }
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
