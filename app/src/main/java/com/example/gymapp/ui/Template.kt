package com.example.gymapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.gymapp.R
import com.example.gymapp.ui.Activities.Activity
import com.example.gymapp.ui.components.DrawerContent.DrawerContent
import com.example.gymapp.ui.components.footer.Footer
import com.example.gymapp.ui.components.header.Header
import kotlinx.coroutines.launch

@Composable
fun templateComponente(title: Int = R.string.loren_ipsum){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer (
        drawerState = drawerState,
        drawerContent = {
            DrawerContent { option ->
                println("Seleccionaste: $option") //Cambiarlo para que haga algo (si queremos)
                scope.launch { drawerState.close() }
            }
        },
        gesturesEnabled = true
    ) {
        // Contenido principal de la pantalla
        Scaffold(
            topBar = {
                Header(
                    title = title,
                    onMenuClick = { scope.launch { drawerState.open() } } // Abre el drawer al pulsar el icono
                )
            },
            content = { paddingValues ->
                Activity(modifier = Modifier.padding(paddingValues))
            },
            bottomBar = {
                Footer()
            }
        )
    }
}

