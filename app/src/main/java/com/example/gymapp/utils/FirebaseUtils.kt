@file:Suppress("UNUSED_EXPRESSION")

package com.example.gymapp.utils

import android.util.Log
import androidx.navigation.NavController
import com.example.gymapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await

object FirebaseUtils{
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }


    suspend fun login(email: String, password: String, navController: NavController): Boolean {
        var result = false
        try {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password).await()
            loadDataUser(navController)
            result = true
        } catch (e: Exception) {
           result = false
        }
        return result
    }

    private suspend fun loadDataUser(navController: NavController) {
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()

        val user = auth.currentUser
        if (user != null) {
            try {
                val document = firestore.collection("Users").document(user.uid).get().await()
                if (document.exists()) {
                    val userModel = User(
                        uid = user.uid,
                        name = document.getString("name").orEmpty(),
                        surname = document.getString("surname").orEmpty(),
                        email = user.email.orEmpty(),
                        image = "",
                        enrollment = document.getString("enrollment").orEmpty(),
                        expirationEnrollment = document.getString("expirationEnrollment").orEmpty(),
                        birthdate = document.getString("birthdate").orEmpty(),
                        points = document.getString("points").orEmpty(),
                    )
                    PreferencesManager.saveUser(navController.context, userModel)
                }
            } catch (e: Exception) {
                Log.e("loadDataUser", "Error al cargar los datos del usuario: ${e.message}")
            }
        }
    }


    // Función para cerrar sesión
    fun logout() {
        auth.signOut()
    }

    // Función para verificar si un usuario ya está autenticado
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    // Función para obtener los datos de las rutinas de ejercicios de la semana pasada por parámtro
    suspend fun fetchRoutineTableAsJson(weekName: String = "Week1"): String? {
        return try {
            val user = auth.currentUser
                ?: throw IllegalStateException("Authentication error. Please log in again")

            val document = firestore.collection("Users")
                .document(user.uid)
                .collection("RoutineTable")
                .document(weekName)
                .get()
                .await()

            if (document.exists()) {
                val data = document.data
                if (data != null) {
                    Gson().toJson(data)
                } else {
                    null
                }
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun fetchClassForDay(dayOfWeek: String): String? {
        return try {
            val document = firestore.collection("Class")
                .document(dayOfWeek)
                .get()
                .await()

            if (document.exists()) {
                val data = document.data
                if (data != null) {
                    Gson().toJson(data)
                } else {
                    null
                }
            } else {
                Log.w("fetchClassForDay", "No existe el documento para el día: $dayOfWeek")
                null
            }
        } catch (e: Exception) {
            Log.e("fetchClassForDay", "Error al obtener la clase del día: ${e.message}")
            null
        }
    }

}