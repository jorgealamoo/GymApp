@file:Suppress("UNUSED_EXPRESSION")

package com.example.gymapp.utils

import android.util.Log
import androidx.navigation.NavController
import com.example.gymapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseUtils{
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun login(email: String, password: String, navController: NavController): Boolean {
        var result: Boolean = false

        if (auth.currentUser != null) {
            return true
        }
        if(email.isBlank() || password.isBlank()){
            return result
        }else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    result = false
                } else {
                    loadDataUser(navController)
                    result = true
                }
            }
        }
        return result
    }

    private fun loadDataUser(navController: NavController) {
        val user = auth.currentUser
        if(user != null) {
            firestore.collection("Users").document(user.uid)
                .get().addOnSuccessListener{ document ->
                    if(document.exists()){
                        val userModel = User(
                            uid = user.uid,
                            name = document.getString("name").toString(),
                            surname = document.getString("surname").toString(),
                            email = user.email.toString(),
                            image = user.photoUrl?.toString() ?: "URL no disponible",
                            enrollment = document.getString("enrollment").toString(),
                            expirationEnrollment = document.getString("expirationEnrollment").toString()
                        )

                        PreferencesManager.saveUser(navController.context, userModel)
                    }
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
}