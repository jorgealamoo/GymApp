package com.example.gymapp.models

data class User(
    val uid: String,
    val name: String,
    val surname: String,
    val email: String,
    val image: String,
    val enrollment: String,
    val expirationEnrollment: String,
    val birthdate: String,
    val points: String
)

