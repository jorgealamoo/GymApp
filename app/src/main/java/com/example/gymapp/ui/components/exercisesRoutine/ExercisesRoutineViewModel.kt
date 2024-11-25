package com.example.gymapp.ui.components.exercisesRoutine

import androidx.lifecycle.ViewModel
import com.example.gymapp.R

class ExercisesRoutineViewModel : ViewModel() {
    private val routines = exercisesRoutineList

    fun getRoutinesByName(name: String) : ExercisesRoutine {
        return routines.find { it.routineName == name }
            ?: ExercisesRoutine("lorem_ipsum", R.drawable.ic_launcher_foreground, R.string.loren_ipsum)
    }
}