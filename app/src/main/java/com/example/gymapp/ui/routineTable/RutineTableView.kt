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
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.components.DrawerContent.DrawerContent
import com.example.gymapp.components.footer.Footer
import com.example.gymapp.components.header.Header
import com.example.gymapp.components.routineWeek.RoutineWeekUncompleted
import com.example.gymapp.ui.theme.White
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
                RoutineTableContent(modifier = Modifier.padding(paddingValues))
            },
            bottomBar = {
                Footer(navController = navController)
            }
        )
    }
}

@Composable
fun RoutineTableContent(modifier: Modifier = Modifier) {
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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoutineWeekUncompleted(
                weekDay = 1,
                exercises = listOf(
                    "legs" to false,
                    "legs" to true,
                    "legs" to false,
                    "legs" to true,
                    "legs" to false
                ),
                onProgression = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            RoutineWeekUncompleted(
                weekDay = 2,
                exercises = listOf(
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false
                ),
                onProgression = false
            )

            Spacer(modifier = Modifier.height(30.dp))

            RoutineWeekUncompleted(
                weekDay = 3,
                exercises = listOf(
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false
                ),
                onProgression = false
            )

            Spacer(modifier = Modifier.height(30.dp))

            RoutineWeekUncompleted(
                weekDay = 4,
                exercises = listOf(
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false,
                    "legs" to false
                ),
                onProgression = false
            )
        }
    }
}
@Composable
@Preview
fun RoutineTablePreview(){
    RoutineTableContent()
}