package com.example.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gymapp.ui.Activities.Activity
import com.example.gymapp.ui.components.activityInfo.ActivityInfo
import com.example.gymapp.ui.theme.GymAppTheme
import com.example.gymapp.ui.profile.Profile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivityInfo(
                activityTitle = R.string.loren_ipsum,
                gymCity = R.string.lpgc_u_know,
                date = "21/11/2024",
                startTime = "00:00",
                endTime = "00:00",
                coach = "Manolo Rodríguez",
                room = "Studio 1",
                activityImage = R.drawable.ciclo_indoor_image,
                description = R.string.bigger_loren_ipsum
            )
        }
    }
}