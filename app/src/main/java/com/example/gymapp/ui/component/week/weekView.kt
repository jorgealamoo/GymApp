package com.example.gymapp.ui.component.week

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.GymYellow

@Composable
fun WeekDaysSelector() {
    val days = listOf("M", "T", "W", "R", "F", "S", "U")
    var selectedDay by remember { mutableStateOf<String?>("M") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        days.forEach { day ->
            Box(
                modifier = Modifier
                    .size(40.dp) // Tamaño de cada día
                    .background(
                        color = if (selectedDay == day) GymRed else Color.Transparent,
                        shape = CircleShape // Forma redonda
                    )
                    .clickable { selectedDay = day }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (selectedDay == day) GymYellow else GymRed
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewWeekDaysSelector() {
    WeekDaysSelector()
}
