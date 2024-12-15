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
                        image = document.getString("image").toString(),
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

    suspend fun getDocumentField(collection: String, documentId: String, field: String): String? {
        return try {
            // Obtener el documento
            val documentSnapshot = firestore
                .collection(collection)
                .document(documentId)
                .get()
                .await()

            // Obtener el campo específico si existe
            if (documentSnapshot.exists()) {
                val value = documentSnapshot.get(field)

                val documentData = value
                val json = Gson().toJson(documentData)

                json
            } else {
                Log.w("FirebaseUtils", "El documento no existe: $documentId")
                null
            }
        } catch (e: Exception) {
            Log.e("FirebaseUtils", "Error al obtener el campo: ${e.message}")
            null
        }
    }

    suspend fun fetchDocuments(collectionName: String): String?{
        return try {
            val documentsSnapshot = firestore.collection(collectionName).get().await()
            if (!documentsSnapshot.isEmpty){
                val documenList = documentsSnapshot.documents.mapNotNull {
                    document -> document.data
                }
                Gson().toJson(documenList)
            }else{
                Log.d("fetchAllDocuments", "No existen documentos en la colección: $collectionName")
                null
            }
        }catch (e: Error){
            Log.e("fetchAllDocuments", "Error al obtener los documentos: ${e.message}")
            return null
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
                val activityMap = userDocument.data?.get("SubscribeActivity").toString()


                if (activityMap != null && activityMap.contains(id)) {
                    return true
                }
            }

            return false
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    suspend fun activitySubscribe(value: Any): Boolean {
        return try {
            val user = auth.currentUser
                ?: throw IllegalStateException("Authentication error. Please log in again")

            firestore.collection("Users")
                .document(user.uid)
                .update("SubscribeActivity", FieldValue.arrayUnion(value))
                .await()
            true
        } catch (e: Exception) {
            Log.e("FirebaseUtils", "Error al agregar elemento al array dentro del map: ${e.message}")
            false
        }
    }

    suspend fun activityUnsubscribe(value: Any): Boolean {
        return try {
            val user = auth.currentUser
                ?: throw IllegalStateException("Authentication error. Please log in again")

            firestore.collection("Users")
                .document(user.uid)
                .update("SubscribeActivity", FieldValue.arrayRemove(value))
                .await()
            true
        } catch (e: Exception) {
            Log.e("FirebaseUtils", "Error al eliminar elemento del array dentro del map: ${e.message}")
            false
        }
    }

    suspend fun updateIsCompletedInRoutineTable(weekName: String, routineName: String, isCompleted: Boolean): Boolean {
        return try {
            val user = auth.currentUser
                ?: throw IllegalStateException("Authentication error. Please log in again")

            val routineDocument = firestore.collection("Users")
                .document(user.uid)
                .collection("RoutineTable")
                .document(weekName)

            val snapshot = routineDocument.get().await()

            if (snapshot.exists()) {
                val routineTable = snapshot.data?.toMutableMap()
                if (routineTable != null && routineTable.containsKey(routineName)) {
                    val routineData = routineTable[routineName] as? ArrayList<Any>
                    if (routineData != null && routineData.size > 2) {
                        routineData[2] = isCompleted
                        routineTable[routineName] = routineData

                        routineDocument.set(routineTable).await()
                        Log.d("FirebaseUtils", "isCompleted actualizado correctamente para $routineName")
                        true
                    } else {
                        Log.e("FirebaseUtils", "Estructura inválida para la rutina: $routineName")
                        false
                    }
                } else {
                    Log.e("FirebaseUtils", "La rutina $routineName no existe en la tabla de rutinas")
                    false
                }
            } else {
                Log.e("FirebaseUtils", "El documento de la tabla de rutinas no existe: $weekName")
                false
            }
        } catch (e: Exception) {
            Log.e("FirebaseUtils", "Error al actualizar isCompleted en $routineName: ${e.message}")
            false
        }
    }


}