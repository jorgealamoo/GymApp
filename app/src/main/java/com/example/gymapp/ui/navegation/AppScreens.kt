package com.example.gymapp.ui.navegation


/*
    Añadir las pantallas
 */
sealed class AppScreens(val route: String) {
    object ProfileScreen: AppScreens("profile_screen")
    object Activity: AppScreens("activity_screen")
    object ExerciseRoutine: AppScreens("exercise_routine_screen")
}