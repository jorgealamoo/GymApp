package com.example.gymapp.ui.components.routineWeek

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
import com.example.gymapp.R
import com.example.gymapp.ui.components.routineDay.RoutineDayCompleted
import com.example.gymapp.ui.components.routineDay.RoutineDayCompletedWeek
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White

@Composable
fun RoutineWeekUncompleted(
    weekDay: Int = 0,
    day1Exercise: Int,
    day2Exercise: Int,
    day3Exercise: Int,
    day4Exercise: Int,
    day5Exercise: Int,
    onProgression: Boolean = false
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

        RoutineDayCompleted(dayOfWeek = 1, exercise = day1Exercise)

        Spacer(modifier = Modifier.width(10.dp))

        RoutineDayCompleted(dayOfWeek = 2, exercise = day2Exercise)

        Spacer(modifier = Modifier.width(10.dp))

        RoutineDayCompleted(dayOfWeek = 3, exercise = day3Exercise)

        Spacer(modifier = Modifier.width(10.dp))

        RoutineDayCompleted(dayOfWeek = 4, exercise = day4Exercise)

        Spacer(modifier = Modifier.width(10.dp))

        RoutineDayCompleted(dayOfWeek = 5, exercise = day5Exercise)
    }
}

@Composable
@Preview
fun RoutineWeekUncompletedPreview(){
    RoutineWeekUncompleted(
        weekDay = 1,
        day1Exercise = R.string.abs,
        day2Exercise = R.string.legs,
        day3Exercise = R.string.chest,
        day4Exercise = R.string.arms,
        day5Exercise = R.string.back,
        onProgression = true
    )
}