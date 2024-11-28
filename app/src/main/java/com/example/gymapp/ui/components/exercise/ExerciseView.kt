package com.example.gymapp.ui.components.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.theme.GymRed
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymapp.ui.theme.Black

@Composable
fun Exercise(
    exerciseName: String,
    repetitions: Int = 0,
    weight: Int = 0,
    viewModel: ExerciseViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val exercise = viewModel.getExerciseByName(exerciseName)

    Row(
        modifier = modifier
            .size(320.dp, 90.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(exercise.exerciseImage),
            contentDescription = stringResource(exercise.exerciseString)
        )

        Column(
            modifier = Modifier
                .padding(start = 25.dp)
        ) {
            Text(
                text = stringResource(exercise.exerciseString),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = GymRed
            )

            Row {
                Text(
                    text = "x$repetitions",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )

                Text(
                    text = "$weight Kg",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    modifier = Modifier.padding(start = 35.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun ExercisePreview(){
    Exercise(
        exerciseName = "leg_press",
        repetitions = 12,
        weight = 80
    )
}