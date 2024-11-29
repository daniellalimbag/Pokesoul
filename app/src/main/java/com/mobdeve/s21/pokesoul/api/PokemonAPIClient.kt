package com.mobdeve.s21.pokesoul.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonAPIClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokemonAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonAPI::class.java)
    }
}