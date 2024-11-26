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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.ui.Activities.ContentActivity
import com.example.gymapp.ui.components.DrawerContent.DrawerContent
import com.example.gymapp.ui.components.footer.Footer
import com.example.gymapp.ui.components.header.Header
import com.example.gymapp.ui.components.profile_content.ProfileContent
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun Profile(
    name: String = "Prueba",
    surname: String = "Prueba",
    email: String = "Prueba@gmail.com",
    birthdate: String = "11/06/1942",
    enrollmentDate: String = "11/06/1972",
    currentEnrollmentExpiration: String = "11/06/1973",
    navController: NavController,
    profileImage: Int = R.drawable.user,
){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
        DrawerContent { option ->
            println("Seleccionaste: $option")
            scope.launch { drawerState.close() }
        }
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
                    name = name,
                    surname = surname,
                    email = email,
                    birthdate = birthdate,
                    enrollmentDate = enrollmentDate,
                    currentEnrollmentExpiration = currentEnrollmentExpiration,
                    profileImage = profileImage,
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
    name: String = R.string.loren_ipsum.toString(),
    surname: String = R.string.loren_ipsum.toString(),
    email: String = R.string.loren_ipsum.toString(),
    birthdate: String = R.string.loren_ipsum.toString(),
    enrollmentDate: String = R.string.loren_ipsum.toString(),
    currentEnrollmentExpiration: String = R.string.loren_ipsum.toString(),
    profileImage: Int = R.drawable.user,
    navController: NavController,
    modifier: Modifier = Modifier
    ) {
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

            UserInfo(name, surname, profileImage)

            Spacer(modifier = Modifier.height(20.dp))

            ProfileContent(title = R.string.email, email)

            Spacer(modifier = Modifier.height(20.dp))

            ProfileContent(title = R.string.birthdate, birthdate)

            Spacer(modifier = Modifier.height(20.dp))

            ProfileContent(title = R.string.enrollment_date, enrollmentDate)

            Spacer(modifier = Modifier.height(20.dp))

            ProfileContent(title = R.string.current_expiration, currentEnrollmentExpiration)

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
