package com.example.gymapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.components.InformationDialog.InformationDialog
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.FirebaseUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun Login(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showModal by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val systemUiController = rememberSystemUiController()
    val navigationBarColor = Color(0xFFFF9885)

    SideEffect {
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = true
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable { keyboardController?.hide() }
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
            LoadingScreen()
        } else {
            Column(
                modifier = Modifier
                    .size(350.dp, 500.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(GymRed.copy(alpha = 0.6f))
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gymapplogo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(140.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))

                RoundTextField(R.string.username, email, { email = it }, Icons.Filled.Person)
                Spacer(modifier = Modifier.height(16.dp))
                RoundTextField(
                    R.string.password,
                    password,
                    { password = it },
                    Icons.Filled.Lock,
                    PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true
                            val success = FirebaseUtils.login(email, password, navController)
                            isLoading = false
                            if (success) {
                                navController.navigate("home_screen")
                            } else {
                                showModal = true
                            }
                        }
                    },
                    modifier = Modifier
                        .width(170.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Black)
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        color = White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        if (showModal) {
            InformationDialog(
                R.string.login_result,
                R.string.login_error,
                onDismiss = { showModal = false }
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = GymRed)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cargando...",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun RoundTextField(
    placeholder: Int,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None,

    ){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(stringResource(id = placeholder), fontWeight = FontWeight.Bold, fontSize = 18.sp) },
        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.width(265.dp),
        shape = RoundedCornerShape(25.dp),
        leadingIcon = {
            Icon(leadingIcon, contentDescription = null, tint = Color.Black)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            focusedPlaceholderColor = White
        ),
        visualTransformation = visualTransformation,
        singleLine = true
    )
}