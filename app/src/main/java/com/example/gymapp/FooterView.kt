package com.example.gymapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gymapp.ui.theme.GymRed

@Composable
fun Footer(iconSize: Dp = 70.dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(GymRed),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = R.string.calendar.toString(),
            modifier = Modifier.size(iconSize)
                .padding(10.dp)
                .clickable {  }
        )

        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = R.string.home.toString(),
            modifier = Modifier.size(iconSize)
                .padding(10.dp)
                .clickable {  }
        )

        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = R.string.profile.toString(),
            modifier = Modifier.size(iconSize)
                .padding(10.dp)
                .clickable {  }
        )
    }
}

@Composable
@Preview
fun FooterPreview(){
    Footer()
}