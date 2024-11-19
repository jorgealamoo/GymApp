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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ActivityInfo(activityTitle: Int,
                 gymCity: Int,
                 date: String,
                 startTime: String,
                 endTime: String,
                 coach: String = "Coach Name",
                 room: String
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

        ScheduleAndCoach(parseDate(date), startTime, endTime, coach)

        Text(
            text = stringResource(R.string.room),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = GymRed,
            modifier = Modifier.padding(25.dp, 30.dp, 0.dp, 0.dp)
        )

        Text(
            text = room,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Black,
            modifier = Modifier.padding(25.dp, 3.dp, 0.dp, 0.dp)
        )
    }
}

fun parseDate(dateString: String): Date {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.parse(dateString) ?: Date()
}

@Composable
fun ScheduleAndCoach(date: Date, startTime: String, endTime: String, coach: String){
    val dateFormat = SimpleDateFormat("EEEE, dd/MM", Locale.getDefault())
    val formattedDate = dateFormat.format(date)
    val dayWithCapitalLetter = formattedDate.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }

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

            Text(
                text = "$dayWithCapitalLetter\n$startTime - $endTime",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = Modifier.padding(top = 3.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 90.dp)
        ) {
            Text(
                text = stringResource(R.string.coach),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = GymRed
            )

            Text(
                text = coach,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }
}

@Composable
@Preview
fun ActivityInfoPreview(){
    ActivityInfo(activityTitle = R.string.loren_ipsum,
        gymCity = R.string.lpgc_u_know,
        date = "21/11/2024",
        startTime = "00:00",
        endTime = "00:00",
        coach = "Manolo Rodríguez",
        room = "Studio 1"
    )
}