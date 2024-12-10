package com.example.gymapp.components.week

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
fun WeekDaysSelector(
    selectedDay: String,
    onDaySelected: (String) -> Unit
) {
    val days = listOf("M", "T", "W", "R", "F", "S")

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
                    .size(40.dp)
                    .background(
                        color = if (selectedDay == day) GymRed else Color.Transparent,
                        shape = CircleShape
                    )
                    .clickable { onDaySelected(day) } // Notificar cambio al padre
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

