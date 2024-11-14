package com.example.gymapp.ui.components.profile_content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProfileContent(title: String = "Title", content: Any = "Content"){
    val contentText = when (content) {
        is String -> content
        is Date -> SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(content)
        else -> content.toString()
    }

    val contentFontSize = when {
        contentText.length > 37 -> 14.sp
        contentText.length > 31 -> 16.sp
        contentText.length > 26 -> 18.sp
        else -> 22.sp
    }

    Column(
        modifier = Modifier
            .size(350.dp, 90.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(White.copy(0.6f)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = GymRed,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(25.dp, 10.dp, 0.dp, 0.dp)
        )

        Text(
            text = contentText,
            color = Black,
            fontSize = contentFontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(25.dp, 10.dp)
        )
    }
}

@Composable
@Preview
fun ProfileContentPreview(){
    ProfileContent()
}
