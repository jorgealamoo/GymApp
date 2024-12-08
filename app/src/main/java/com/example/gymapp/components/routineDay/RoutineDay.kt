package com.example.gymapp.components.routineDay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.GymYellow
import com.example.gymapp.ui.theme.White
import com.google.gson.Gson

@Composable
fun RoutineDay(
    dayOfWeek: Int,
    exerciseImage: Int,
    exercise: Int,
    completed: Boolean = false,
    exerciseName: String = "",
    exercisesList: List<Map<String, Map<String, Int>>> = emptyList(),
    navController: NavController
){
    val backgroundColor = if (completed) GymRed else GymYellow.copy(0.6f)
    val displayImage = if (completed) R.drawable.white_tick else exerciseImage

    val gson = Gson()
    val exercisesJson = gson.toJson(exercisesList)

    Column(
        modifier = Modifier
            .size(40.dp, 105.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable {
                navController.navigate(
                    "exerciseRoutineView?$exerciseName=LegDay&day=$dayOfWeek&exercisesJson=$exercisesJson"
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = dayOfWeek.toString(),
            color = if (completed) White else GymRed,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painter = painterResource(displayImage),
            contentDescription = if (completed) stringResource(R.string.completed) else stringResource(exercise),
            modifier = Modifier.size(35.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(exercise),
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

/*
@Composable
@Preview
fun RoutineDayPreview(){
    RoutineDay(dayOfWeek = 1, exerciseImage = R.drawable.piernas, exercise = R.string.legs, false)
}
*/