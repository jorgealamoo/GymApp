package com.example.gymapp.components.activityInfo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.R
import com.example.gymapp.models.Exercise
import com.example.gymapp.models.ExerciseRepository
import com.example.gymapp.ui.login.LoadingScreen
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.FirebaseUtils
import kotlinx.coroutines.launch
import org.json.JSONObject


@Composable
fun ActivityInfoView(navController: NavHostController,
                     dia: String?,
                     id: String?)
{
    var isLoading by remember { mutableStateOf(true) }
    var exercise by remember { mutableStateOf<String?>(null) }
    var exerciseJsonObject by remember { mutableStateOf<JSONObject?>(null) }
    var exerciseData by remember { mutableStateOf<Exercise?>(null) }
    var isAdded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(dia, id) {
        isAdded = FirebaseUtils.isIdInDayList(dia.toString(), id.toString())
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)

    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = 0.6f)
        )
        if (isLoading) {
            LoadingScreen()
            LaunchedEffect(Unit) {
                exercise = takeExercise(dia.toString(), id.toString())
                exerciseJsonObject = exercise?.let {
                    try {
                        JSONObject(it)
                    } catch (e: Exception) {
                        Log.e("ActivityInfo", "Error al convertir el JSON: ${e.message}")
                        null
                    }
                }
                val exerciseName = exerciseJsonObject?.getString("Name")
                if (exerciseName != null) {
                    exerciseData = ExerciseRepository.getExerciseByName(exerciseName)
                }
                isLoading = false
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .statusBarsPadding(),
            ) {
                Row {
                    Text(
                        text = exerciseJsonObject?.getString("Name").toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black,
                        modifier = Modifier.padding(25.dp, 30.dp, 0.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.close),
                            contentDescription = stringResource(R.string.close),
                            modifier = Modifier.height(38.dp)
                        )
                    }
                }

                /**/
                Text(
                    text = stringResource(R.string.hora) + exerciseJsonObject?.getString("Hora").toString(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = GymRed,
                    modifier = Modifier.padding(25.dp, 5.dp, 0.dp, 0.dp)
                )


                Image(
                    painter = painterResource(exerciseData?.image ?: R.drawable.clases_gap),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 20.dp)
                        .graphicsLayer(alpha = 0.5f)
                )

                ActivityDescription(exerciseData?.infoText ?: "")


            }

            if (isAdded){
                Button(
                    onClick = {
                        scope.launch {
                            unSubscribeActivity(
                                id = id.toString(),
                                day = dia.toString(),
                                ocupado = exerciseJsonObject?.getString("Ocupado")?.toInt() ?: 0
                            )
                            isAdded = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .align(Alignment.BottomCenter)
                        .padding(10.dp, 0.dp, 10.dp, 20.dp)
                ) {
                    Text(
                        text = stringResource(R.string.reserved),
                        color = White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }else{
                if ((exerciseJsonObject?.getString("Disponibilidad")?.toInt() ?: 0) >= (exerciseJsonObject?.getString("Ocupado")?.toInt() ?: 0)){
                    Button(
                        onClick = {
                            scope.launch {
                                subscribeToActivity(
                                    id = id.toString(),
                                    day = dia.toString(),
                                    ocupado = exerciseJsonObject?.getString("Ocupado")?.toInt() ?: 0
                                )
                                isAdded = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = GymRed),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .align(Alignment.BottomCenter)
                            .padding(10.dp, 0.dp, 10.dp, 20.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.book),
                            color = White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

suspend fun unSubscribeActivity(id: String, day: String, ocupado: Int) {
    val ocupado = ocupado - 1
    FirebaseUtils.updateField(day,id,"Ocupado",ocupado)
    FirebaseUtils.activityUnsubscribe(id)
}

suspend fun subscribeToActivity(day: String, id: String, ocupado: Int) {
    val ocupado = ocupado + 1
    FirebaseUtils.updateField(day,id,"Ocupado",ocupado)
    FirebaseUtils.activitySubscribe(id)
}

suspend fun takeExercise(day: String, id: String): String? {
    return FirebaseUtils.getDocument(day,id)
}


@Composable
fun ActivityDescription(description: String){
    Column(
        modifier = Modifier
            .padding(10.dp, 20.dp, 10.dp, 100.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(White.copy(0.6f))
    ) {
        Text(
            text = stringResource(R.string.description),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = GymRed,
            modifier = Modifier.padding(15.dp, 15.dp, 0.dp, 0.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = description,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Black,
            modifier = Modifier.padding(start = 15.dp, top = 3.dp, end = 15.dp, bottom = 15.dp)
        )
    }
}

@Preview
@Composable
fun preview(){
    var navController = rememberNavController()
    ActivityInfoView(navController = navController, dia = "Martes", id = "pepe")
}