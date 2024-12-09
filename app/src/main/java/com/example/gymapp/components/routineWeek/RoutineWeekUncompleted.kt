package com.example.gymapp.components.routineWeek

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.components.exercisesRoutine.ExercisesRoutineViewModel
import com.example.gymapp.components.routineDay.RoutineDay
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White

@Composable
fun RoutineWeekUncompleted(
    weekDay: Int = 0,
    exercises: List<Pair<String, Boolean>> = emptyList(),
    onProgression: Boolean = false,
    viewModel: ExercisesRoutineViewModel = viewModel(),
    navController: NavController
){
    val weekColor = if (onProgression) GymRed else Black

    Row(
        modifier = Modifier
            .size(305.dp, 135.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(White.copy(0.6f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.height(180.dp)
        ) {
            // Línea vertical
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .height(40.dp)
                    .width(3.dp)
                    .background(GymRed)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(R.string.week) + " $weekDay",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = weekColor,
                modifier = Modifier
                    .rotateVertically()
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        exercises.forEachIndexed { index, exercisePair ->
            if (index < 5) {
                val (exerciseName, isCompleted) = exercisePair

                RoutineDay(
                    dayOfWeek = index + 1,
                    exerciseImage = viewModel.getRoutinesByName(exerciseName).routineIcon,
                    exercise = viewModel.getRoutinesByName(exerciseName).routineString,
                    completed = isCompleted,
                    navController = navController
                )
            }

            if (index != exercises.lastIndex && index < 4) {
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

/*
@Composable
@Preview
fun RoutineWeekUncompletedPreview(){
    RoutineWeekUncompleted(
        weekDay = 1,
        exercises = listOf(
            "legs" to false,
            "legs" to true,
            "legs" to false,
            "legs" to true,
            "legs" to false
        ),
        onProgression = true
    )
}
 */