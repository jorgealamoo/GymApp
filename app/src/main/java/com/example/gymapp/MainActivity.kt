package com.example.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gymapp.ui.theme.GymAppTheme
import com.example.gymapp.ui.component.week.WeekDaysSelector
import com.example.gymapp.ui.profile.Profile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymAppTheme {
                Profile(
                    name = "Name",
                    surname = "Tengo Un Apellido Muy Largo",
                    email = "email@email.com",
                    birthdate = "20/09/2001",
                    enrollmentDate = "01/03/2023",
                    currentEnrollmentExpiration = "01/12/2024"
                )
            }
        }
    }
}