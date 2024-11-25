package com.example.gymapp.ui.components.exercisesRoutine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymapp.R
import com.example.gymapp.ui.components.exercise.ExerciseViewModel
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White

@Composable
fun ExerciseRoutineView(
    routineName: String,
    day: Int = 0,
    viewModel: ExercisesRoutineViewModel = viewModel()
) {
    val routine = viewModel.getRoutinesByName(routineName)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = 0.6f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            Image(
                painter = painterResource(routine.routineImage),
                contentDescription = stringResource(routine.routineString),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().graphicsLayer(alpha = 0.7f)
            )

            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 140.dp)
            ) {
                Text(
                    text = stringResource(routine.routineString),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )

                Text(
                    text = stringResource(R.string.day) + " $day",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = GymRed
                )
            }
        }
    }
}

@Composable
@Preview
fun ExerciseRoutineViewPreview() {
    ExerciseRoutineView(routineName = "legs", day = 1)
}