package com.example.gymapp.ui.routineTable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.components.DrawerContent.DrawerContent
import com.example.gymapp.components.footer.Footer
import com.example.gymapp.components.header.Header
import com.example.gymapp.components.routineWeek.RoutineWeekUncompleted
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.FirebaseUtils
import com.google.gson.Gson
import kotlinx.coroutines.launch

@Composable
fun RoutineTable(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer (
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController = navController)
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                Header(
                    title = R.string.routine_table,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            },
            content = { paddingValues ->
                RoutineTableContent(modifier = Modifier.padding(paddingValues), navController = navController)
            },
            bottomBar = {
                Footer(navController = navController)
            }
        )
    }
}

@Composable
fun RoutineTableContent(modifier: Modifier = Modifier, navController: NavController) {
    val scope = rememberCoroutineScope()
    var routinesList: List<Pair<String, Boolean>> = emptyList()
    var exercisesList: List<Map<String, Map<String, Int>>> = emptyList()

    var routineTableState by remember { mutableStateOf<Map<String, List<String>>?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Cargar los datos de Firebase
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val json = FirebaseUtils.fetchRoutineTableAsJson("Week1")
                if (json != null) {
                    routineTableState = Gson().fromJson(json, Map::class.java) as Map<String, List<String>>?
                    isLoading = false
                } else {
                    errorMessage = "No data for this week"
                    isLoading = false
                }
            } catch (e: Exception) {
                errorMessage = "Data loading error: ${e.message}"
                isLoading = false
            }
        }
    }

    routineTableState?.forEach { (key, value) ->
        val routineDetails = value as List<*>
        val category = routineDetails[0] as? String ?: "Null"
        val exercises = routineDetails[1] as? Map<String, Map<String, Int>>
        val completedFlag = routineDetails[2] as? Boolean ?: false
        routinesList = routinesList + (category to completedFlag)
        if (exercises != null) {
            exercisesList = exercisesList + exercises
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = 0.6f)
        )

        if (isLoading) {
            // Mostrar un indicador de carga
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Loading...")
            }
        } else if (errorMessage != null) {
            // Mostrar el mensaje de error
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(errorMessage ?: "Unknown Error")
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoutineWeekUncompleted(
                    weekDay = 1,
                    exercises = routinesList,
                    onProgression = true,
                    exercisesList = exercisesList,
                    navController = navController
                )

                Spacer(modifier = Modifier.height(17.dp))

                RoutineWeekUncompleted(
                    weekDay = 2,
                    exercises = routinesList,
                    onProgression = false,
                    exercisesList = exercisesList,
                    navController = navController
                )

                Spacer(modifier = Modifier.height(17.dp))

                RoutineWeekUncompleted(
                    weekDay = 3,
                    exercises = routinesList,
                    onProgression = false,
                    exercisesList = exercisesList,
                    navController = navController
                )

                Spacer(modifier = Modifier.height(17.dp))

                RoutineWeekUncompleted(
                    weekDay = 4,
                    exercises = routinesList,
                    onProgression = false,
                    exercisesList = exercisesList,
                    navController = navController
                )
            }
        }
    }
}

/*
@Composable
@Preview
fun RoutineTablePreview(){
    RoutineTableContent()
}
 */