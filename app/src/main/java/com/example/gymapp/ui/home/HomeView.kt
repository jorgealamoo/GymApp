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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.gymapp.R
import com.example.gymapp.components.DrawerContent.DrawerContent
import com.example.gymapp.components.card.Card
import com.example.gymapp.components.footer.Footer
import com.example.gymapp.components.header.Header
import com.example.gymapp.components.profile_content.ProfileContent
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.FirebaseUtils
import com.example.gymapp.utils.PreferencesManager
import kotlinx.coroutines.launch

@Composable

fun Home(navController: NavController, modifier: Modifier = Modifier){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer (
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController = navController)
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                Header(
                    title = R.string.home,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            },
            content = { paddingValues ->
                ContentHome(modifier = Modifier.padding(paddingValues),navController = navController)
            },
            bottomBar = {
                Footer(navController = navController)
            }
        )
    }
}


@Composable
fun ContentHome(modifier: Modifier = Modifier,
         profileImage: Int = R.drawable.user,
                navController: NavController)
{
    var expanded = remember { mutableStateOf(false) }
    val options = listOf("Las Palmas de Gran Canaria", "Santa Cruz de Tenerife", "Puerto del Rosario")
    var selectedOption = remember { mutableStateOf(options[0]) }
    val user = PreferencesManager.getUser(navController.context)
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
                    painter = rememberImagePainter(user?.image),
                    contentDescription = "Profile image",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape) // Esto hará la imagen redonda
                )


                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "${user?.name} ${user?.surname}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card("${user?.points}", navController)
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Transparent)
                    ) {
                        TextButton(
                            onClick = { expanded.value = true },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = selectedOption.value,
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Abrir menú"
                            )
                        }

                    }
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                        modifier = Modifier
                            .width(312.dp)
                            .align(Alignment.TopStart)

                    ) {
                        options.filter { it != selectedOption.value }.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedOption.value = option // Actualiza la opción seleccionada
                                    expanded.value = false // Cierra el menú
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}