package com.example.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gymapp.ui.Activities.Activity
import com.example.gymapp.ui.components.DrawerContent.DrawerContent
import com.example.gymapp.ui.components.header.Header
import com.example.gymapp.ui.theme.GymAppTheme
import com.example.gymapp.ui.profile.Profile
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen() {
        // Estado del Drawer
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer (
            drawerState = drawerState,
            drawerContent = {
                DrawerContent { option ->
                    println("Seleccionaste: $option")
                    scope.launch { drawerState.close() } // Cierra el drawer al seleccionar una opción
                }
            },
            gesturesEnabled = true // Permite cerrarlo deslizando hacia la derecha
        ) {
            // Contenido principal de la pantalla
            Scaffold(
                topBar = {
                    Header(
                        title = R.string.loren_ipsum,
                        onMenuClick = { scope.launch { drawerState.open() } } // Abre el drawer al pulsar el icono
                    )
                },
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Contenido principal")
                    }
                }
            )
        }
    }

}