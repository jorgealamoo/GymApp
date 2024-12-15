package com.example.gymapp.ui.pointsStore

import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.components.DrawerContent.DrawerContent
import com.example.gymapp.components.footer.Footer
import com.example.gymapp.components.header.Header
import com.example.gymapp.components.pointsProductCard.PointsProductCard
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.FirebaseUtils
import com.example.gymapp.utils.PreferencesManager
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

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
                ContentPointsStore(modifier = Modifier.padding(paddingValues), navController = navController)
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
    navController: NavController
){
    val user = PreferencesManager.getUser(navController.context)
    var isLoading by remember { mutableStateOf(true) }
    var items by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        items = FirebaseUtils.fetchDocuments("Catalog")
        Log.d("Catalog", "ContentPointsStore: ${items}")
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(R.string.your_points) + " ${user?.points}",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = GymRed
            )

            Spacer(modifier = Modifier.height(25.dp))

            addContentToStore(items)

        }
    }
}


@Composable
fun addContentToStore(items: String?) {

    val jsonItems = remember(items) {
        try {
            items?.let { JSONArray(it) }
        } catch (e: Exception) {
            null
        }
    }

    jsonItems?.let { array ->
        for (i in 0 until array.length()) {
            val jsonObject: JSONObject = array.getJSONObject(i)
            val name = jsonObject.optString("Name", "Unknown")
            val price = jsonObject.optString("Price", "0")

            PointsProductCard(name, price)
        }
    } ?: return
}
