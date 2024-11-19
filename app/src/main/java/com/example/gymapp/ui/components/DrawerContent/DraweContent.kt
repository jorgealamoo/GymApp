package com.example.gymapp.ui.components.DrawerContent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
    onOptionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight().background(Color.Gray).padding(16.dp)
    ) {
        Text(
            text = "Opción 1",
            modifier = Modifier.clickable { onOptionSelected("Opción 1") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Opción 2",
            modifier = Modifier.clickable { onOptionSelected("Opción 2") }
        )
    }
}
