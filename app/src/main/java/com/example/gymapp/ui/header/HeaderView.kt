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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymapp.R
import com.example.gymapp.ui.theme.GymAppTheme
import com.example.gymapp.ui.theme.GymRed

@Composable
fun Header(title : String = ""){
    Row (
        modifier
        =  Modifier
            .background(GymRed)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = R.string.calendar.toString(),
            modifier = Modifier.size(15.dp)
                .padding(10.dp)
                .clickable {  })

        Text( text = title)
    }
}

@Preview(showBackground = false)
@Composable
fun HeaderPreView(){
    GymAppTheme {
        Header()
    }
}