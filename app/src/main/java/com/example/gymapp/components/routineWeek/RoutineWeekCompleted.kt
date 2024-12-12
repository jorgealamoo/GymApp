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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.components.routineDay.RoutineDayCompletedWeek
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White

@Composable
fun RoutineWeekCompleted(
    weekDay: Int = 0,
    exercisesStrings: List<Int> = emptyList()
){
    Row(
        modifier = Modifier
            .size(305.dp, 135.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(GymRed),
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
                    .background(White)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(R.string.week) + " $weekDay",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier
                    .rotateVertically()
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        exercisesStrings.forEachIndexed { index, exerciseString ->
            RoutineDayCompletedWeek(
                dayOfWeek = index + 1,
                exercise = exerciseString
            )
            if (index != exercisesStrings.lastIndex && index < 4) {
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

fun Modifier.rotateVertically(clockwise: Boolean = true): Modifier {
    val rotate = rotate(if (clockwise) -90f else 90f)

    val adjustBounds = layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }
    return rotate then adjustBounds
}

/*
@Composable
@Preview
fun RoutineWeekCompletedPreview(){
    RoutineWeekCompleted(
        weekDay = 1,
        day1Exercise = R.string.abs,
        day2Exercise = R.string.legs,
        day3Exercise = R.string.chest,
        day4Exercise = R.string.arms,
        day5Exercise = R.string.back,
    )
}
*/