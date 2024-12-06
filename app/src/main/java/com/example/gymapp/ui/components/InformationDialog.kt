package com.example.gymapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.ui.theme.GymRed

@Composable
fun InformationDialog(title: Int, content: Int){
    var showModal by remember { mutableStateOf(true) }

    if(showModal) {
        AlertDialog(
            onDismissRequest = { showModal = false },
            title = {
                Text(text = stringResource(title), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            },
            text = {
                Text(stringResource(content))
            },
            confirmButton = {
                Button(
                    onClick = { showModal = false },
                    colors = ButtonDefaults.buttonColors(GymRed),
                    modifier = Modifier.size(80.dp, 50.dp)
                ) {
                    Text("OK", fontWeight = FontWeight.ExtraBold)
                }
            }
        )
    }
}