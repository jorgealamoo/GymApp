package com.example.gymapp.components.pointsProductCard

import android.content.Context
import android.provider.ContactsContract.Data
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.pointsStore.DataItemsStore
import com.example.gymapp.ui.pointsStore.ItemsStoreProvider
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymOrange
import com.example.gymapp.ui.theme.White

@Composable
fun PointsProductCard(name: String = stringResource(R.string.loren_ipsum), pointsCost: String = "0") {

    val image = ItemsStoreProvider.getImageByName(name)
    Row(
        modifier = Modifier
            .size(315.dp, 125.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(White.copy(0.6f))
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = image?.toInt() ?: R.drawable.ic_launcher_foreground),
            contentDescription = name + " Image",
            modifier = Modifier
                .size(105.dp)
                .padding(start = 15.dp)
        )

        Column(
            modifier = Modifier.padding(start = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "${pointsCost} ${stringResource(R.string.points)}",
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