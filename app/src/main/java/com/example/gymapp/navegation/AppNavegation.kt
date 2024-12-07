package com.example.gymapp.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.components.exercisesRoutine.ExerciseRoutineView
import com.example.gymapp.ui.Activities.Activity
import com.example.gymapp.ui.home.Home
import com.example.gymapp.ui.login.Login
import com.example.gymapp.ui.pointsStore.PointsStore
import com.example.gymapp.ui.profile.Profile
import com.example.gymapp.ui.routineTable.RoutineTable
import com.example.gymapp.ui.theme.GymAppTheme

@Composable
fun AppNavegation(){
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination = AppScreens.LoginScreen.route){
        composable(route = AppScreens.Activity.route) {
            Activity(navController = navController)
        }
        composable(route = AppScreens.ProfileScreen.route) {
            Profile(navController = navController)
        }
        composable(route = AppScreens.ExerciseRoutine.route) {
            ExerciseRoutineView(navController = navController)
        }
        composable(route = AppScreens.HomeScreen.route) {
            Home(navController = navController)
        }
        composable(route = AppScreens.LoginScreen.route) {
            Login(navController = navController)
        }
        composable(route = AppScreens.PointsStoreScreen.route) {
            PointsStore(navController = navController)
        }
        composable(route = AppScreens.RoutineTableScreen.route) {
            RoutineTable(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    GymAppTheme {
        AppNavegation()
    }
}