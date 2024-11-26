package com.example.gymapp.ui.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.ui.Activities.Activity
import com.example.gymapp.ui.Activities.ContentActivity
import com.example.gymapp.ui.profile.ContentProfile
import com.example.gymapp.ui.profile.Profile

@Composable
fun AppNavegation(){
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination = AppScreens.Activity.route){
        composable(route = AppScreens.Activity.route) {
            Activity(navController = navController)
        }
        composable(route = AppScreens.ProfileScreen.route) {
            Profile(navController = navController)
        }
    }
}