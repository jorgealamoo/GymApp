package com.example.gymapp.models

import com.example.gymapp.R

data class Exercise(
    val name: String,
    val imageResId: Int,
    val infoText: String,
    val image: Int
)

object ExerciseRepository {
    val exercises = listOf(
        Exercise(
            name = "Gap",
            imageResId = R.drawable.icon_abs,
            infoText = "El ejercicio GAP se centra en Glúteos, Abdominales y Piernas. Es ideal para tonificar y fortalecer estas zonas del cuerpo.",
            image = R.drawable.clases_gap
        ),
        Exercise(
            name = "Zumba",
            imageResId = R.drawable.icon_zumba,
            infoText = "Zumba combina movimientos de baile con ejercicios aeróbicos. Es perfecto para quemar calorías y divertirse al ritmo de la música.",
            image = R.drawable.clases_zumba
        ),
        Exercise(
            name = "Cycle in Door",
            imageResId = R.drawable.icon_spining,
            infoText = "Cycle In Door es una clase de spinning en interiores, diseñada para mejorar la resistencia cardiovascular y fortalecer las piernas.",
            image = R.drawable.clases_cicle_in_door
        )
    )

    // Función auxiliar para obtener un ejercicio por nombre
    fun getExerciseByName(name: String): Exercise? {
        return exercises.find { it.name == name }
    }
}
