package com.example.gymapp.components.DrawerContent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.utils.FirebaseUtils

@Composable
fun DrawerContent(
    navController: NavController
) {
       Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(Color.White)
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
           dataLink(
               textId = R.string.routine_table,
               onClick = { navController.navigate("routine_table_screen") }
           )
           dataLink(
               textId = R.string.points_store,
               onClick = { navController.navigate("points_store_screen") }
           )
           dataLink(
               textId = R.string.MyActivity,
               onClick = { navController.navigate("my_classes_screen") }
           )

        Spacer(modifier = Modifier.weight(1f))
           dataLink(
               textId = R.string.logOut,
               icon = Icons.Default.ExitToApp,
               onClick = {
                   FirebaseUtils.logout()
                   navController.navigate("login_screen")
               },
                color = GymRed
           )

    }
}

@Composable
fun dataLink(modifier: Modifier = Modifier, icon: ImageVector = Icons.Default.Add, textId: Int = R.string.loren_ipsum, onClick: () -> Unit = {}, color: Color = Color.Unspecified){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 3.dp.toPx()
                )
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

    ){
        TextButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            contentPadding = PaddingValues()
        ){
            Icon(
                imageVector = icon,
                contentDescription = R.string.loren_ipsum.toString(),
                modifier = Modifier.padding(start = 10.dp, end = 20.dp),
                tint = color
            )
            Text(
                text = stringResource(id = textId),
                modifier = Modifier
                    .padding(end = 60.dp),
                fontSize = 20.sp,
                color = color
            )
        }
    }
}
