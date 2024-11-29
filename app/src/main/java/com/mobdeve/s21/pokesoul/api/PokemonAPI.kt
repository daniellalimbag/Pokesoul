package com.mobdeve.s21.pokesoul.api

import com.mobdeve.s21.pokesoul.model.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPI {
    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int
    ): Call<PokemonListResponse>
}
