package com.example.gymapp.ui.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.theme.GymAppTheme
import com.example.gymapp.ui.theme.GymRed

@Composable
fun Header(title : String = "Se te olvida el titulo paleto"){
    Row (
        modifier =  Modifier
            .background(Color.White.copy(alpha = 0.5f))
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = R.string.menu.toString(),
            modifier = Modifier.size(40.dp)
                .clickable {  println("Hola mundo") }
        )

        Text(
            modifier = Modifier.padding(start = 20.dp),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = GymRed
            ),
            text = title


        )
    }
}

@Preview(showBackground = false)
@Composable
fun HeaderPreView(){
    GymAppTheme {
        Header()
    }
}