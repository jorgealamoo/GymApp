package com.example.gymapp.ui.components.activityInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymOrange
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White
import java.util.Date

@Composable
fun ActivityInfo(activityTitle: Int,
                 gymCity: Int
                 ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Text(
            text = stringResource(activityTitle),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Black,
            modifier = Modifier.padding(25.dp, 30.dp, 0.dp, 0.dp)
        )

        Text(
            text = stringResource(gymCity),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = GymRed,
            modifier = Modifier.padding(25.dp, 5.dp, 0.dp, 0.dp)
        )

        ScheduleAndCoach()
    }
}

@Composable
fun ScheduleAndCoach(date: String = "hola", startTime: String = "que", endTime: String = "tal"){
    Row(
        modifier = Modifier
            .padding(25.dp, 40.dp, 0.dp, 0.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.schedule),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = GymRed
            )
            

        }
    }
}

@Composable
@Preview
fun ActivityInfoPreview(){
    ActivityInfo(activityTitle = R.string.loren_ipsum,
        gymCity = R.string.lpgc_u_know
    )
}