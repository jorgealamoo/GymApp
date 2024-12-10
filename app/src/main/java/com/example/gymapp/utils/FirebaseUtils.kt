@file:Suppress("UNUSED_EXPRESSION")

package com.example.gymapp.utils

import android.util.Log
import androidx.navigation.NavController
import com.example.gymapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await
import kotlin.system.exitProcess

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
            // Obtiene todos los documentos de la colección del día de la semana
            val documentsSnapshot = firestore.collection(dayOfWeek)
                .get()
                .await()

            if (!documentsSnapshot.isEmpty) {
                // Crear una lista de mapas (documento ID y contenido)
                val documentsList = documentsSnapshot.documents.mapNotNull { document ->
                    val data = document.data
                    if (data != null && data.isNotEmpty()) {
                        // Incluir el nombre del documento y los datos del documento
                        mapOf("documentId" to document.id, "data" to data)
                    } else {
                        null
                    }
                }

                if (documentsList.isNotEmpty()) {
                    // Convertir la lista de documentos a una cadena JSON
                    Gson().toJson(documentsList)
                } else {
                    Log.w("fetchClassForDay", "Todos los documentos en la colección están vacíos")
                    null
                }
            } else {
                Log.w("fetchClassForDay", "No existen documentos en la colección: $dayOfWeek")
                null
            }
        } catch (e: Exception) {
            Log.e("fetchClassForDay", "Error al obtener la colección: ${e.message}")
            null
        }
    }

    suspend fun getDocument(collection: String, documentId: String): String? {
        return try {
            // Obtener el documento usando la colección y el ID del documento
            val document = firestore.collection(collection).document(documentId).get().await()

            if (document.exists()) {
                Log.d("FirebaseUtils", "Documento obtenido con éxito: ${document.id}")

                val documentData = document.data
                val json = Gson().toJson(documentData)

                json
            } else {
                Log.w("FirebaseUtils", "El documento no existe: $documentId")
                null
            }
        } catch (e: Exception) {
            Log.e("FirebaseUtils", "Error al obtener el documento: ${e.message}")
            null
        }
    }

    // Modificar un campo de un documento
    suspend fun updateField(collection: String, documentId: String, field: String, value: Any): Boolean {
        return try {
            // Actualizar el campo específico del documento
            val updateMap = mapOf(field to value)
            firestore.collection(collection).document(documentId).update(updateMap).await()
            Log.d("FirebaseUtils", "Campo actualizado exitosamente: $field en $documentId")
            true
        } catch (e: Exception) {
            Log.e("FirebaseUtils", "Error al actualizar el campo: ${e.message}")
            false
        }
    }

    suspend fun isIdInDayList(dayOfWeek: String, id: String): Boolean {

        return try {
            // Obtener el documento del usuario
            val user = auth.currentUser
                ?: throw IllegalStateException("Authentication error. Please log in again")

            val userDocument = firestore.collection("Users")
                .document(user.uid).get().await()

            if (userDocument.exists()) {
                val daysMap = userDocument.data?.get("SuscribeActivity") as? Map<String, List<String>>


                val dayList = daysMap?.get(dayOfWeek)

                if (dayList != null && dayList.contains(id)) {
                    return true
                }
            }

            return false
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    suspend fun activitySuscribe(mapField: String, arrayField: String, value: Any): Boolean {
        return try {
            val user = auth.currentUser
                ?: throw IllegalStateException("Authentication error. Please log in again")

            firestore.collection("Users")
                .document(user.uid)
                .update("$mapField.$arrayField", FieldValue.arrayUnion(value))
                .await()
            true
        } catch (e: Exception) {
            Log.e("FirebaseUtils", "Error al agregar elemento al array dentro del map: ${e.message}")
            false
        }
    }

    suspend fun activityUnsuscribe(mapField: String, arrayField: String, value: Any): Boolean {
        return try {
            // Usamos arrayRemove para eliminar un valor del array dentro del Map
            val user = auth.currentUser
                ?: throw IllegalStateException("Authentication error. Please log in again")

            firestore.collection("Users")
                .document(user.uid)
                .update("$mapField.$arrayField", FieldValue.arrayRemove(value))
                .await()
            true
        } catch (e: Exception) {
            Log.e("FirebaseUtils", "Error al eliminar elemento del array dentro del map: ${e.message}")
            false
        }
    }


}