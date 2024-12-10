package com.example.gymapp.utils
import android.content.Context
import com.example.gymapp.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PreferencesManager {

    private const val PREF_NAME = "GymAppPrefs"
    private const val USER_KEY = "user"

    fun saveUser(context: Context, user: User) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val userJson = Gson().toJson(user)
        editor.putString(USER_KEY, userJson)
        editor.apply()
    }

    fun isUserSaved(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.contains(USER_KEY)
    }

    fun getUser(context: Context): User? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val userJson = sharedPreferences.getString(USER_KEY, null)
        return if (userJson != null) {
            Gson().fromJson(userJson, object : TypeToken<User>() {}.type)
        } else {
            null
        }
    }
}
