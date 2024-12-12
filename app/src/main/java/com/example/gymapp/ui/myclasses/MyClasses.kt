package com.example.gymapp.ui.myclasses

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.R
import com.example.gymapp.components.DrawerContent.DrawerContent
import com.example.gymapp.components.activityCard.ActivityCardView
import com.example.gymapp.components.footer.Footer
import com.example.gymapp.components.header.Header
import com.example.gymapp.components.week.WeekDaysSelector
import com.example.gymapp.ui.login.LoadingScreen
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.FirebaseUtils
import com.example.gymapp.utils.PreferencesManager
import kotlinx.coroutines.launch
import org.json.JSONArray

@Composable
fun MyClasses(navController: NavHostController) {
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
                    title = R.string.MyActivity,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            },
            content = { paddingValues ->
                ContentMyclasses(navController = navController,
                    modifier = Modifier.padding(paddingValues))
            },
            bottomBar = {
                Footer(navController = navController)
            }
        )
    }
}

@Composable
fun ContentMyclasses(navController: NavHostController, modifier: Modifier) {
    val scrollState = rememberScrollState()
    var selectedDay by remember { mutableStateOf("M") }
    var classDataJson by remember { mutableStateOf<String?>(null) }
    var exerciseDataJson by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    var user = PreferencesManager.getUser(navController.context)
    val dayMap = mapOf(
        "M" to "Monday",
        "T" to "Tuesday",
        "W" to "Wednesday",
        "R" to "Thursday",
        "F" to "Friday",
        "S" to "Saturday",
    )

    LaunchedEffect(Unit) {
        if (user != null) {
            Log.d("TAG", "ContentMyclasses: ${user.uid}")
            classDataJson = FirebaseUtils.getDocumentField("Users", user.uid.toString(), "SubscribeActivity")
        }
        isLoading = false
    }

    LaunchedEffect(selectedDay) {
        isLoading = true
        exerciseDataJson = FirebaseUtils.fetchClassForDay(dayMap[selectedDay].toString())
        isLoading = false
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
        if(isLoading){
            LoadingScreen()
        }else {
            Log.d("TAG", "${classDataJson}")
            Column(
                modifier = Modifier.verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                WeekDaysSelector(
                    selectedDay = selectedDay,
                    onDaySelected = { day -> selectedDay = day }
                )

                InsertMyActivity(exerciseDataJson, classDataJson, dayMap[selectedDay].toString(), navController)
            }
        }
    }
}

@Composable
fun InsertMyActivity(exerciseDataJson: String?, classDataJson: String?, selectedDay: String, navController: NavHostController) {
        if(classDataJson.isNullOrEmpty()) return
        if(exerciseDataJson.isNullOrEmpty()) return

    val jsonArray = JSONArray(exerciseDataJson)
    for (i in 0 until jsonArray.length()) {
        if ( classDataJson.contains(jsonArray.getJSONObject(i).getString("documentId").toString())) {

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
}

@Preview
@Composable
fun PreviewMyClasses(){
    val navController = rememberNavController()
    MyClasses(navController)
}