package com.example.gymapp.components.exercisesRoutine

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.gymapp.R
import com.example.gymapp.components.exercise.Exercise
import com.example.gymapp.navegation.AppScreens
import com.example.gymapp.ui.theme.Black
import com.example.gymapp.ui.theme.GymRed
import com.example.gymapp.ui.theme.White
import com.example.gymapp.utils.FirebaseUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.coroutines.launch

@Composable
fun ExerciseRoutineView(
    exercises: List<String> = emptyList(),
    viewModel: ExercisesRoutineViewModel = viewModel(),
    navController: NavController,
    navBackStackEntry: NavBackStackEntry
) {
    val routineName = navBackStackEntry.arguments?.getString("routineName") ?: "Unknown"
    val day = navBackStackEntry.arguments?.getInt("dayOfWeek") ?: 1
    val routine = viewModel.getRoutinesByName(routineName)
    var exercisesList by remember { mutableStateOf<List<Map<String, Map<String, Int>>>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isCompleted by remember { mutableStateOf(false) }
    var selectedRoutineName by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    val navigationBarColor = Color(0xFF9885)

    SideEffect {
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = true
        )
    }

    LaunchedEffect(Unit) {
        try {
            val json = FirebaseUtils.fetchRoutineTableAsJson("Week1")
            Log.d("FirebaseData", "JSON: $json")

            if (json != null) {
                val routineTableState: Map<String, List<Any>> = Gson().fromJson(json, object : TypeToken<Map<String, List<Any>>>() {}.type)
                Log.d("FirebaseData", "Parsed Data: $routineTableState")

                val filteredExercises = routineTableState
                    .filter { (routineKey, details) ->
                        val category = details.getOrNull(0) as? String ?: ""
                        if (category == routineName) {
                            selectedRoutineName = routineKey // Guarda el nombre de la rutina seleccionada
                        }
                        Log.d("FirebaseData", "Category: $category Routine: $selectedRoutineName")
                        category == routineName
                    }
                    .flatMap { (_, details) ->
                        val exercises = details.getOrNull(1) as? Map<String, Map<String, Int>> ?: emptyMap()
                        Log.d("FirebaseData", "Exercises: $exercises")

                        exercises.map { (exerciseName, exerciseDetails) ->
                            mapOf(exerciseName to exerciseDetails)
                        }
                    }

                exercisesList = filteredExercises
                Log.d("FirebaseData", "Filtered Exercises: $exercisesList")

                val routineDetails = routineTableState.values.firstOrNull { details ->
                    (details.getOrNull(0) as? String) == routineName
                }
                isCompleted = routineDetails?.getOrNull(2) as? Boolean ?: false
            } else {
                errorMessage = "No data found for Week1"
            }
        } catch (e: Exception) {
            errorMessage = "Error loading data: ${e.message}"
            Log.e("FirebaseData", "Error: ${e.message}", e)
        } finally {
            isLoading = false
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    } else if (errorMessage != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = errorMessage ?: "Unknown error", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
                    .statusBarsPadding()
                    .navigationBarsPadding()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer(alpha = 0.6f)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                ) {
                    Image(
                        painter = painterResource(routine.routineImage),
                        contentDescription = stringResource(routine.routineString),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize().graphicsLayer(alpha = 0.7f)
                    )

                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 140.dp)
                    ) {
                        Text(
                            text = stringResource(routine.routineString),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )

                        Text(
                            text = stringResource(R.string.day) + " $day",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = GymRed
                        )

                    }
                }

                Column(
                    modifier = Modifier
                        .padding(top = 220.dp)
                        .fillMaxWidth()
                        .background(White.copy(0.6f))
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = exercisesList.size.toString() + " " + stringResource(R.string.exercises),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black,
                        modifier = Modifier.padding(start = 20.dp, bottom = 5.dp)
                    )

                    exercisesList.forEach { exercise ->
                        exercise.forEach { (exerciseName, details) ->
                            var repetitionsNumber: Int = 0
                            var weightNumber: Int = 0
                            details.forEach { (key, value) ->
                                if (key == "repetitions") repetitionsNumber = value
                                if (key == "weight") weightNumber = value
                            }
                            Exercise(
                                exerciseName = exerciseName,
                                repetitions = repetitionsNumber,
                                weight = weightNumber,
                                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(90.dp))
                }

                IconButton(
                    onClick = {
                        navController.navigate(AppScreens.RoutineTableScreen.route)
                    },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(White.copy(0.7f))
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector =  Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.return_button),
                        tint = GymRed,
                        modifier = Modifier.size(50.dp)
                    )
                }

                Button(
                    onClick = {
                        if (!isCompleted) {
                            isCompleted = true
                            selectedRoutineName?.let { routineName ->
                                coroutineScope.launch {
                                    val result = FirebaseUtils.updateIsCompletedInRoutineTable("Week1", routineName, isCompleted)
                                    if (result) {
                                        Log.d("ExerciseRoutineView", "isCompleted actualizado correctamente para $routineName")
                                        navController.navigate(AppScreens.RoutineTableScreen.route)
                                    } else {
                                        Log.e("ExerciseRoutineView", "Error al actualizar isCompleted para $routineName")
                                    }
                                }
                            }
                        } else {
                            isCompleted = false
                            selectedRoutineName?.let { routineName ->
                                coroutineScope.launch {
                                    val result = FirebaseUtils.updateIsCompletedInRoutineTable("Week1", routineName, isCompleted)
                                    if (result) {
                                        Log.d("ExerciseRoutineView", "isCompleted actualizado correctamente para $routineName")
                                    } else {
                                        Log.e("ExerciseRoutineView", "Error al actualizar isCompleted para $routineName")
                                    }
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!isCompleted) GymRed else White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .align(Alignment.BottomCenter)
                        .padding(10.dp, 0.dp, 10.dp, 20.dp)
                ) {
                    Text(
                        text = if (!isCompleted) stringResource(R.string.complete) else stringResource(R.string.completed),
                        color = if (!isCompleted) White else GymRed,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
    }
}