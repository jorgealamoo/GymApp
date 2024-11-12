package com.example.gymapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymOrange
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.GymYellow
import com.example.gymapp.ui.theme.White

@Composable
fun Login(){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
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
                contentDescription = R.string.app_logo.toString(),
                modifier = Modifier.size(140.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text(stringResource(id = R.string.username), fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.width(265.dp),
                shape = RoundedCornerShape(25.dp),
                leadingIcon = {
                    Icon(Icons.Filled.Person, contentDescription = null, tint = Color.Black)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    focusedPlaceholderColor = White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(stringResource(id = R.string.password), fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.width(265.dp),
                shape = RoundedCornerShape(25.dp),
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = null, tint = Color.Black)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    focusedPlaceholderColor = White
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { println("Login done") },
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

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.forgot_password),
                color = Black,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline
            )
        }
    }

}

@Composable
@Preview
fun LoginPreview(){
    Login()
}