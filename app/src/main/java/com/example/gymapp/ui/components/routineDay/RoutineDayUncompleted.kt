package com.example.gymapp.ui.components.routineDay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.GymYellow

@Composable
fun RoutineDayUncompleted(dayOfWeek: Int, exerciseImage: Int, exercise: Int){
    Column(
        modifier = Modifier
            .size(40.dp, 105.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(GymYellow.copy(0.6f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = dayOfWeek.toString(),
            color = GymRed,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painter = painterResource(exerciseImage),
            contentDescription = stringResource(R.string.completed),
            modifier = Modifier.size(26.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(exercise),
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
@Preview
fun RoutineDayUncompletedPreview(){
    RoutineDayUncompleted(dayOfWeek = 1, exerciseImage = R.drawable.piernas, exercise = R.string.legs)
}