package com.example.gymapp.ui.profile

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.components.header.Header
import com.example.gymapp.ui.components.profile_content.ProfileContent
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymRed),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(title = R.string.profile)

        Spacer(modifier = Modifier.height(20.dp))

        UserInfo()

        Spacer(modifier = Modifier.height(20.dp))

        ProfileContent(title = R.string.email)

        Spacer(modifier = Modifier.height(20.dp))

        ProfileContent(title = R.string.birthdate)

        Spacer(modifier = Modifier.height(20.dp))

        ProfileContent(title = R.string.enrollment_date)

        Spacer(modifier = Modifier.height(20.dp))

        ProfileContent(title = R.string.current_expiration)

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(R.string.change_password),
            fontSize = 22.sp,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun UserInfo(name: String = "Name", surname: String = "Surname") {
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
                text = surname,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.weight(0.3f))
    }
}

@Composable
@Preview
fun ProfilePreview() {
    Profile()
}