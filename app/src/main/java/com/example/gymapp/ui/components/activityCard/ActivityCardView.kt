package com.example.gymapp.ui.components.activityCard


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.ui.theme.White
import com.example.gymapp.R
import com.example.gymapp.ui.theme.GymOrange

@Composable
fun ActivityCardView (image:Int = R.drawable.image_removebg_preview, hora: String = "00/00", totalCapacity: Int = 0, ability: Int = 0,
                      exerciseClass: String ="Invalid exercise", studio: String = "Studio X",content: @Composable () -> Unit = {}) {
    Box(
        modifier = Modifier
            .width(315.dp)
            .height(122.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(color = White.copy(alpha = 0.67f))
            .clickable(onClick = {content}),
        contentAlignment = Alignment.CenterStart,
    ){
        Row (
            modifier = Modifier
                .padding(start = 10.dp),
        ){
            Image(
                painter = painterResource(id = image),
                contentDescription = "TODO",
                alignment = Alignment.Center,
                modifier = Modifier
                    .height(84.dp)
                    .height(84.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                if (matchesFormat(hora)) {
                    Text(
                        text = hora.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }else{
                    Text(
                        text = "Invalid Format",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
                Text(
                    text = (totalCapacity.toString() + "/" + ability.toString()
                            + " " + stringResource(id = R.string.spots)),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = GymOrange
                )

                Text(text = exerciseClass,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(text = studio,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}
fun matchesFormat(input: String): Boolean {
    val regex = Regex("^\\d{2}/\\d{2}$")
    return regex.matches(input)
}

@Preview
@Composable
fun PreviewClassCard() {
    ActivityCardView()
}