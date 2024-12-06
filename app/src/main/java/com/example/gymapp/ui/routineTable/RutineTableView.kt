package com.example.gymapp.ui.routineTable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymapp.R
import com.example.gymapp.ui.components.routineWeek.RoutineWeekUncompleted
import com.example.gymapp.ui.theme.White

@Composable
fun RoutineTable(){
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

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            Spacer(modifier = Modifier.height(30.dp))

            RoutineWeekUncompleted(
                weekDay = 2,
                exercises = listOf(
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false
                ),
                onProgression = false
            )

            Spacer(modifier = Modifier.height(30.dp))

            RoutineWeekUncompleted(
                weekDay = 3,
                exercises = listOf(
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false
                ),
                onProgression = false
            )

            Spacer(modifier = Modifier.height(30.dp))

            RoutineWeekUncompleted(
                weekDay = 4,
                exercises = listOf(
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false
                ),
                onProgression = false
            )
        }
    }
}
@Composable
@Preview
fun RoutineTablePreview(){
    RoutineTable()
}