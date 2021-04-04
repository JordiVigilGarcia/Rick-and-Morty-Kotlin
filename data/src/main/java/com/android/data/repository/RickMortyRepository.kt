package com.android.data.repository

import android.content.Context
import com.android.data.commons.BaseRepository
import com.android.data.commons.Constants


class RickMortyRepository(private val context: Context) :
    BaseRepository() {

    fun getFavCharacter(): Int {
        val sharedPref = context.getSharedPreferences(
            Constants.SP_USER, Context.MODE_PRIVATE
        )
        return sharedPref.getInt(Constants.CHARACTER_FAV, -1) ?: -1
    }

    fun setFavCharacter(pos: Int) {
        val sharedPref =
            context.getSharedPreferences(Constants.SP_USER, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(Constants.CHARACTER_FAV, pos)
            commit()
        }
    }

    fun getName(): String {
        val sharedPref = context.getSharedPreferences(
            Constants.SP_USER, Context.MODE_PRIVATE
        )
        return sharedPref.getString(Constants.PREF_NAME, "") ?: ""
    }

    fun setName(name: String) {
        val sharedPref =
            context.getSharedPreferences(Constants.SP_USER, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(Constants.PREF_NAME, name)
            commit()
        }
    }

    fun getUsername(): String {
        val sharedPref = context.getSharedPreferences(
            Constants.SP_USER, Context.MODE_PRIVATE
        )
        return sharedPref.getString(Constants.PREF_USERNAME, "") ?: ""
    }

    fun setUsername(surname: String) {
        val sharedPref =
            context.getSharedPreferences(Constants.SP_USER, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(Constants.PREF_USERNAME, surname)
            commit()
        }
    }

    fun getDescription(): String {
        val sharedPref = context.getSharedPreferences(
            Constants.SP_USER, Context.MODE_PRIVATE
        )
        return sharedPref.getString(Constants.PREF_DESCRIP, "") ?: ""
    }

    fun setDescription(description: String) {
        val sharedPref =
            context.getSharedPreferences(Constants.SP_USER, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(Constants.PREF_DESCRIP, description)
            commit()
        }
    }

}