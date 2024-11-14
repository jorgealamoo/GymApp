package com.example.gymapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymapp.R
import com.example.gymapp.ui.components.header.Header
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymRed.copy(0.6f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(title = R.string.profile)

        Spacer(modifier = Modifier.height(20.dp))

        UserInfo()
    }
}

@Composable
fun UserInfo() {
    Row(
        modifier = Modifier
            .size(335.dp, 120.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(White.copy(0.6f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(

        ) {

        }
    }
}

@Composable
@Preview
fun ProfilePreview() {
    Profile()
}