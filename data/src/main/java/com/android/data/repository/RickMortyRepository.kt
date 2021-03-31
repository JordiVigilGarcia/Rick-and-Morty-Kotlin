package com.android.data.repository

import android.content.Context
import com.android.data.commons.BaseRepository
import com.android.data.commons.Constants
import com.android.data.remote.RickMortyAPI


class RickMortyRepository(private val context: Context, private val api: RickMortyAPI) :
    BaseRepository() {

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