package com.example.gymapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.components.card.Card
import com.example.gymapp.ui.theme.White

@Composable
fun Home(modifier: Modifier = Modifier,
         profileImage: Int = R.drawable.user,
         name: String = "Name",
         surname: String = "Surname"){

    var expanded = remember { mutableStateOf(false) }
    var selectedOption = remember { mutableStateOf("Seleccionar opción") }
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
                .graphicsLayer(alpha = 0.6f),
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .width(312.dp)
                    .height(65.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(color = White.copy(alpha = 0.60f)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(profileImage),
                    contentDescription = stringResource(R.string.profile_image),
                    modifier = Modifier
                        .size(56.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "$name $surname",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(1)
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(312.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(color = White.copy(alpha = 0.60f)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = stringResource(R.string.gym_name),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { expanded.value = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = selectedOption.value)
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false } // Cerrar el menú al hacer clic fuera
                ) {
                    val options = listOf("Opción 1", "Opción 2", "Opción 3")
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOption.value = option.toString() // Actualiza la opción seleccionada
                                expanded.value = false // Cierra el menú
                            }
                        )
                    }
                }
            }
        }

    }

}

@Composable
@Preview
fun LoginPreview(){
    Home();
}