package com.example.gymapp.models

import com.example.gymapp.R

data class Exercise(
    val name: String,
    val imageResId: Int,
    val infoText: String
)

object ExerciseRepository {
    val exercises = listOf(
        Exercise(
            name = "Gap",
            imageResId = R.drawable.image_removebg_preview,
            infoText = "El ejercicio GAP se centra en Glúteos, Abdominales y Piernas. Es ideal para tonificar y fortalecer estas zonas del cuerpo."
        ),
        Exercise(
            name = "Zumba",
            imageResId = R.drawable.image_removebg_preview,
            infoText = "Zumba combina movimientos de baile con ejercicios aeróbicos. Es perfecto para quemar calorías y divertirse al ritmo de la música."
        ),
        Exercise(
            name = "Cycle in Door",
            imageResId = R.drawable.image_removebg_preview,
            infoText = "Cycle In Door es una clase de spinning en interiores, diseñada para mejorar la resistencia cardiovascular y fortalecer las piernas."
        )
    )

    // Función auxiliar para obtener un ejercicio por nombre
    fun getExerciseByName(name: String): Exercise? {
        return exercises.find { it.name == name }
    }
}
