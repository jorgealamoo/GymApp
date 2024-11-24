package com.example.gymapp.ui.components.exercise

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Exercise(exerciseName: String){
    Row(
        modifier = Modifier
            .size(320.dp, 90.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        "imagen"
    }
}

@Composable
@Preview
fun ExercisePreview(){
    Exercise("Loren ipsum")
}