package com.example.gymapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.components.DrawerContent.DrawerContent
import com.example.gymapp.components.footer.Footer
import com.example.gymapp.components.header.Header
import com.example.gymapp.components.profile_content.ProfileContent
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.PreferencesManager
import kotlinx.coroutines.launch

@Composable
fun Profile(navController: NavController){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
        DrawerContent(navController = navController)
    },
    gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                Header(
                    title = R.string.profile,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            },
            content = { paddingValues ->
                ContentProfile(
                    navController = navController,
                    modifier = Modifier.padding(paddingValues))
            },
            bottomBar = {
                Footer(navController = navController)
            }
        )
    }
}
@Composable
fun ContentProfile(
    profileImage: Int = R.drawable.user,
    navController: NavController,
    modifier: Modifier = Modifier
    ) {
    val user = PreferencesManager.getUser(navController.context)
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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            UserInfo(user?.name.toString(), user?.surname.toString(), profileImage)

            Spacer(modifier = Modifier.height(20.dp))

            ProfileContent(title = R.string.email, user?.email.toString())

            Spacer(modifier = Modifier.height(20.dp))

            ProfileContent(title = R.string.birthdate, user?.birthdate.toString())

            Spacer(modifier = Modifier.height(20.dp))

            ProfileContent(title = R.string.enrollment_date, user?.enrollment.toString())

            Spacer(modifier = Modifier.height(20.dp))

            ProfileContent(title = R.string.current_expiration, user?.expirationEnrollment.toString())

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = stringResource(R.string.change_password),
                fontSize = 22.sp,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
fun UserInfo(name: String = "Name", surname: String = "Surname", profileImage: Int = R.drawable.user) {
    val maxSurnameLength = 15
    val displayedSurname = if (surname.length > maxSurnameLength) {
        surname.take(maxSurnameLength) + "..."
    } else {
        surname
    }

    Row(
        modifier = Modifier
            .size(350.dp, 120.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(White.copy(0.6f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp, 0.dp)
        ) {
            Text(
                text = name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = displayedSurname,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(profileImage),
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier.size(125.dp).padding(0.dp, 0.dp, 25.dp)
        )
    }
}
