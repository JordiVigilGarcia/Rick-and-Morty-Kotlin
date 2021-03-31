package com.android.data.models

data class PokemonsDTO(
    var name: String,
    val tipo: String,
    val peso: String?,
    val region: String?,
    var habitat: String?,
    var pokeimg: String
)