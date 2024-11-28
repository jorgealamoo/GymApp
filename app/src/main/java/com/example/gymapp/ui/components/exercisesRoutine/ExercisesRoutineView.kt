package com.example.gymapp.ui.components.exercisesRoutine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.ui.components.exercise.Exercise
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White

@Composable
fun ExerciseRoutineView(
    routineName: String = "",
    day: Int = 0,
    exercises: List<String> = emptyList(),
    viewModel: ExercisesRoutineViewModel = viewModel(),
    navController: NavController
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

        Column(
            modifier = Modifier
                .padding(top = 220.dp)
                .fillMaxWidth()
                .background(White.copy(0.6f))
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = exercises.size.toString() + " " + stringResource(R.string.exercises),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
                modifier = Modifier.padding(start = 20.dp, bottom = 5.dp)
            )

            exercises.forEach { exerciseName ->
                Exercise(
                    exerciseName = exerciseName,
                    repetitions = 12,
                    weight = 90,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
                )

                Spacer(modifier = Modifier.height(1.dp)
                    .background(Black)
                    .width(305.dp)
                    .align(Alignment.CenterHorizontally)
                )
            }
        }

        IconButton(
            onClick = { println("Return") },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(White.copy(0.7f))
                .size(40.dp)
        ) {
            Icon(
                imageVector =  Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.return_button),
                tint = GymRed,
                modifier = Modifier.size(50.dp)
            )
        }

        Button(
            onClick = { println("Completed") },
            colors = ButtonDefaults.buttonColors(containerColor = GymRed),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter)
                .padding(10.dp, 0.dp, 10.dp, 20.dp)
        ) {
            Text(
                text = stringResource(R.string.complete),
                color = White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}