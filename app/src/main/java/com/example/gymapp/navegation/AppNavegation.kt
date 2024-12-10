package com.example.gymapp.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gymapp.components.activityInfo.ActivityInfoView
import androidx.navigation.navArgument
import com.example.gymapp.components.exercisesRoutine.ExerciseRoutineView
import com.example.gymapp.ui.Activities.Activity
import com.example.gymapp.ui.home.Home
import com.example.gymapp.ui.login.Login
import com.example.gymapp.ui.pointsStore.PointsStore
import com.example.gymapp.ui.profile.Profile
import com.example.gymapp.ui.routineTable.RoutineTable
import com.example.gymapp.ui.theme.GymAppTheme
import com.example.gymapp.utils.FirebaseUtils

@Composable
fun AppNavegation(){
    val navController = rememberNavController()
    var destination = AppScreens.LoginScreen.route
    if(FirebaseUtils.isUserLoggedIn()){
        destination = AppScreens.HomeScreen.route
    }
    NavHost(navController=navController, startDestination = destination){
        composable(route = AppScreens.Activity.route) {
            Activity(navController = navController)
        }
        composable(route = AppScreens.ProfileScreen.route) {
            Profile(navController = navController)
        }
        composable(
            route = AppScreens.ExerciseRoutine.route,
            arguments = listOf(
                navArgument("routineName") { type = NavType.StringType },
                navArgument("dayOfWeek") { type = NavType.IntType },
                navArgument("exercisesList") { type = NavType.StringType }
            )) {backStackEntry ->
            ExerciseRoutineView(navController = navController, navBackStackEntry = backStackEntry)
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
        composable(
            route = AppScreens.ActivityInfoScreen.route,
            arguments = listOf(
                navArgument("dia") { type = NavType.StringType },
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val dia = backStackEntry.arguments?.getString("dia") // Recibir `dia` como String
            val id = backStackEntry.arguments?.getString("id")     // Recibir `id` como Int
            ActivityInfoView(navController = navController, dia = dia, id = id)
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