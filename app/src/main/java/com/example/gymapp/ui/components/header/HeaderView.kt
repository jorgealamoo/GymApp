package com.example.gymapp.ui.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.YellowHeader

@Composable
fun Header(
    title: Int = R.string.spots,
    onMenuClick: () -> Unit = { println("Menú clickeado por defecto") }
    ) {

    val expanded = remember { mutableStateOf(true) }
    Column {
        Row(
            modifier = Modifier
                .background(YellowHeader)
                .fillMaxWidth()
                .padding(10.dp)
                .statusBarsPadding(),
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Icono que despliega el menú
            Box {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.menu),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onMenuClick() }
                )

            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(end = 40.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = GymRed
                ),
                text = stringResource(id = title)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}


