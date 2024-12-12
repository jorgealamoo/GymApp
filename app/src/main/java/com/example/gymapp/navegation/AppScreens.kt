package com.example.gymapp.navegation


/*
    Añadir las pantallas
 */
sealed class AppScreens(val route: String) {
    object ProfileScreen: AppScreens("profile_screen")
    object Activity: AppScreens("activity_screen")
    object ExerciseRoutine : AppScreens("exercise_routine_screen/{routineName}/{dayOfWeek}/{exercisesList}") {
        fun createRoute(routineName: String, dayOfWeek: Int, exercisesList: String): String {
            return "exercise_routine_screen/$routineName/$dayOfWeek/$exercisesList"
        }
    }
    object HomeScreen: AppScreens("home_screen")
    object LoginScreen: AppScreens("login_screen")
    object PointsStoreScreen: AppScreens("points_store_screen")
    object RoutineTableScreen: AppScreens("routine_table_screen")
    object ActivityInfoScreen : AppScreens("activity_info_screen/{dia}/{id}")
    object MyClassesScreen: AppScreens("my_classes_screen")

}