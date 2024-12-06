package com.example.gymapp.components.activityInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.gymapp.R
import com.example.gymapp.ui.theme.Black
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
                 room: String,
                 activityImage: Int,
                 description: Int = R.string.big_loren_ipsum
                 ){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ){
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = 0.6f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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

            Image(
                painter = painterResource(activityImage),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(400.dp, 300.dp)
                    .padding(top = 20.dp)
                    .graphicsLayer(alpha = 0.5f)
            )

            ActivityDescription(description)
        }

        IconButton(
            onClick = { println("Close") },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.close),
                contentDescription = stringResource(R.string.close),
                modifier = Modifier.size(38.dp)
            )
        }

        Button(
            onClick = { println("Booked") },
            colors = ButtonDefaults.buttonColors(containerColor = GymRed),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter)
                .padding(10.dp, 0.dp, 10.dp, 20.dp)
        ) {
            Text(
                text = stringResource(R.string.book),
                color = White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
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
fun ActivityDescription(description: Int){
    Column(
        modifier = Modifier
            .padding(10.dp, 20.dp, 10.dp, 100.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(White.copy(0.6f))
    ) {
        Text(
            text = stringResource(R.string.description),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = GymRed,
            modifier = Modifier.padding(15.dp, 15.dp, 0.dp, 0.dp)
        )

        Text(
            text = stringResource(description),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Black,
            modifier = Modifier.padding(start = 15.dp, top = 3.dp, end = 15.dp, bottom = 15.dp)
        )
    }
}

@Composable
@Preview
fun ActivityInfoPreview(){
    ActivityInfo(
        activityTitle = R.string.loren_ipsum,
        gymCity = R.string.lpgc_u_know,
        date = "21/11/2024",
        startTime = "00:00",
        endTime = "00:00",
        coach = "Manolo Rodríguez",
        room = "Studio 1",
        activityImage = R.drawable.ciclo_indoor_image,
        description = R.string.big_loren_ipsum
    )
}