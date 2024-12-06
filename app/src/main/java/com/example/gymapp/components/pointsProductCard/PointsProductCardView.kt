package com.example.gymapp.components.pointsProductCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymOrange
import com.example.gymapp.ui.theme.White

@Composable
fun PointsProductCard(name: Int = R.string.loren_ipsum, pointsCost: Int = 0, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .size(315.dp, 125.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(White.copy(0.6f))
            .clickable { onClick },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.product_example),
            contentDescription = stringResource(name) + " Image",
            modifier = Modifier.size(105.dp).padding(start = 15.dp)
        )

        Column(
            modifier = Modifier.padding(start = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(name),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = pointsCost.toString() + " " + stringResource(R.string.points),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = GymOrange
            )
        }
    }
}

@Composable
@Preview
fun PointsProductCardPreview() {
    PointsProductCard()
}