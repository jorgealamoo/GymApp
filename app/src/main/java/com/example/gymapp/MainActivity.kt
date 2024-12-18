package com.example.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gymapp.navegation.AppNavegation
import com.example.gymapp.ui.theme.GymAppTheme
import com.example.gymapp.utils.FirebaseUtils
import com.example.gymapp.utils.PreferencesManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavegation()
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