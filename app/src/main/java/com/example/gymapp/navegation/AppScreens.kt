package com.example.gymapp.navegation


/*
    Añadir las pantallas
 */
sealed class AppScreens(val route: String) {
    object ProfileScreen: AppScreens("profile_screen")
    object Activity: AppScreens("activity_screen")
    object ExerciseRoutine: AppScreens("exercise_routine_screen")
    object HomeScreen: AppScreens("home_screen")
    object LoginScreen: AppScreens("login_screen")
    object PointsStoreScreen: AppScreens("points_store_screen")
    object RoutineTableScreen: AppScreens("routine_table_screen")
    object ExerciseRoutineWithParams : AppScreens("exercise_routine_screen/{routineName}/{day}/{exercisesJson}") {
        fun createRoute(routineName: String, day: Int, exercisesJson: String): String {
            return "exercise_routine_screen/$routineName/$day/$exercisesJson"
        }
    }
}