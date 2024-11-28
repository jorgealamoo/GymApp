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
import com.example.gymapp.ui.components.DrawerContent.DrawerContent
import com.example.gymapp.ui.components.activityCard.ActivityCardView
import com.example.gymapp.ui.components.footer.Footer
import com.example.gymapp.ui.components.header.Header
import com.example.gymapp.ui.components.week.WeekDaysSelector
import com.example.gymapp.ui.theme.White
import kotlinx.coroutines.launch


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
fun ContentActivity(navController: NavController, modifier: Modifier = Modifier){
    val scrollState = rememberScrollState()

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

        Column (
            modifier = Modifier
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            WeekDaysSelector()
            InsertActivity()
        }
    }
}
@Composable
fun InsertActivity(){
    for (i in 1..10){
        ActivityCardView()
    }
}
