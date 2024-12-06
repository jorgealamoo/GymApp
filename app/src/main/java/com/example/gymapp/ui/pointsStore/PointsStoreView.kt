package com.example.gymapp.ui.pointsStore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.ui.components.DrawerContent.DrawerContent
import com.example.gymapp.ui.components.footer.Footer
import com.example.gymapp.ui.components.header.Header
import com.example.gymapp.ui.components.pointsProductCard.PointsProductCard
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun PointsStore(navController: NavController){
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
                    title = R.string.points_store,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            },
            content = { paddingValues ->
                ContentPointsStore(modifier = Modifier.padding(paddingValues))
            },
            bottomBar = {
                Footer(navController = navController)
            }
        )
    }
}

@Composable
fun ContentPointsStore(
    modifier: Modifier = Modifier,
    points: Int = 0,
    productList: List<Int> = listOf(
        R.string.loren_ipsum,
        R.string.loren_ipsum,
        R.string.loren_ipsum,
        R.string.loren_ipsum,
        R.string.loren_ipsum,
        R.string.loren_ipsum,
        R.string.loren_ipsum,
        R.string.loren_ipsum,
        R.string.loren_ipsum
    )
){
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(R.string.your_points) + " " + points,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = GymRed
            )

            Spacer(modifier = Modifier.height(25.dp))

            productList.forEach{product ->
                PointsProductCard(name = product)
                Spacer(modifier = Modifier.height(15.dp))
            }

        }
    }
}

@Composable
@Preview
fun PointsStorePreview(){
    ContentPointsStore()
}