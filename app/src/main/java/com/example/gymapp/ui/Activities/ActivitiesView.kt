package com.example.gymapp.ui.Activities

import android.annotation.SuppressLint
import android.util.Log
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
import com.example.gymapp.ui.login.LoadingScreen
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.FirebaseUtils
import kotlinx.coroutines.launch
import org.json.JSONArray
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
        "R" to "Thursday",
        "F" to "Friday",
        "S" to "Saturday",
    )

    LaunchedEffect(selectedDay) {
        Log.d("Prueba","{/TODO/}")
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


            InsertActivity(classDataJson, dayMap[selectedDay].toString(), navController)
        }
    }

}


@Composable
fun InsertActivity(classDataJson: String?, selectedDay: String, navController: NavController,) {
    if(classDataJson.isNullOrEmpty()) return

    val jsonArray = JSONArray(classDataJson)
    for (i in 0 until jsonArray.length()) {
        val document = jsonArray.getJSONObject(i)
        val documentId = document.getString("documentId")
        val data = document.getJSONObject("data")

        val hora = data.getString("Hora")
        val disponibilidad = data.getString("Disponibilidad")
        val ocupado = data.getString("Ocupado")
        val name = data.getString("Name")

        ActivityCardView(
            hora = hora,
            totalCapacity = disponibilidad,
            exerciseClass = name,
            available = ocupado,
            dia = selectedDay,
            id = documentId,
            navController = navController
        )
    }
}

