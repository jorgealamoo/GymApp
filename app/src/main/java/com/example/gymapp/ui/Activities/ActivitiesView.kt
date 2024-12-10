package com.example.gymapp.ui.Activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
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
import com.example.gymapp.components.activityCard.ActivityCardView
import com.example.gymapp.components.footer.Footer
import com.example.gymapp.components.header.Header
import com.example.gymapp.components.week.WeekDaysSelector
import com.example.gymapp.models.ExerciseRepository
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.FirebaseUtils
import kotlinx.coroutines.launch
import org.json.JSONObject


@Composable
fun Activity(navController: NavController, modifier: Modifier = Modifier){
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
                    title = R.string.Activity,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            },
            content = { paddingValues ->
                ContentActivity(navController = navController,
                    modifier = Modifier.padding(paddingValues))
            },
            bottomBar = {
                Footer(navController = navController)
            }
        )
    }
}
@Composable
fun ContentActivity(navController: NavController, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    var selectedDay by remember { mutableStateOf("M") }
    var classDataJson by remember { mutableStateOf<String?>(null) }

    val dayMap = mapOf(
        "M" to "Monday",
        "T" to "Tuesday",
        "W" to "Wednesday",
        "R" to "Thrusday",
        "F" to "Friday",
        "S" to "Saturday",
    )

    LaunchedEffect(selectedDay) {
        classDataJson = FirebaseUtils.fetchClassForDay(dayMap[selectedDay].toString())
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

        Column(
            modifier = Modifier.verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            WeekDaysSelector(
                selectedDay = selectedDay,
                onDaySelected = { day -> selectedDay = day }
            )

            InsertActivity(classDataJson,selectedDay,navController)
        }
    }
}


@Composable
fun InsertActivity(classDataJson: String?, selectedDay: String, navController: NavController,) {

    if (classDataJson.isNullOrEmpty()) return

    val jsonObject = JSONObject(classDataJson) // Convertimos el JSON en un JSONObject
    val keys = jsonObject.keys()              // Obtenemos las claves (nombres de ejercicios)

        keys.forEach { key ->
            val exerciseJson = jsonObject.getJSONObject(key) // Obtenemos el JSON de cada ejercicio
            val hora = exerciseJson.getString("Hora")
            val disponibilidad = exerciseJson.getString("Disponibilidad")
            val ocupado = exerciseJson.getString("Ocupado")
            val exercise = ExerciseRepository.getExerciseByName(key)
                exercise?.let {
                    ActivityCardView(
                        image = exercise.imageResId,
                        hora = hora,
                        totalCapacity = disponibilidad,
                        exerciseClass = key,
                        available = ocupado,
                        exercise = exercise,
                        dia = selectedDay,
                        id = exerciseJson.getString("id")
                    )
                }
        }
}

