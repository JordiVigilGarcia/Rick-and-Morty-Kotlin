package com.android.data.remote

import com.android.data.models.PokemonsDTO
import retrofit2.Response
import retrofit2.http.GET

interface RickMortyAPI {
    @GET("/transactions.json")
    suspend fun getTransactions(): Response<List<PokemonsDTO>>
}