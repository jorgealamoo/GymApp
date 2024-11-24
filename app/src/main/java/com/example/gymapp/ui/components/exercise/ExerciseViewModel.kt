package com.example.gymapp.ui.components.exercise

import androidx.lifecycle.ViewModel
import com.example.gymapp.R

class ExerciseViewModel : ViewModel() {
    private val exercises = exerciseList

    fun getExerciseByName(name: String) : Exercise {
        return exercises.find { it.exerciseName == name }
            ?: Exercise("lorem_ipsum", R.drawable.ic_launcher_foreground, R.string.loren_ipsum)
    }
}