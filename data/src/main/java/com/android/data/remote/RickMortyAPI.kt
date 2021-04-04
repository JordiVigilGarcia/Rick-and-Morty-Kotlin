package com.android.data.remote

import com.android.data.commons.Constants.BASE_API_RICK_MORTY
import com.android.data.commons.Constants.BASE_API_SERVICE_RICK_MORTY
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_API_RICK_MORTY)
    .build()

interface RickMortyAPIService {

    @GET(BASE_API_SERVICE_RICK_MORTY)
    suspend fun getData(): Response<APIResults>
}

object RickMortyAPI {
    val retrofitService : RickMortyAPIService by lazy {
        retrofit.create(RickMortyAPIService::class.java)
    }
}