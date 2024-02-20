package com.ruzgaruludag.rickandmorty.newtask.networking

import com.ruzgaruludag.rickandmorty.newtask.models.CharacterBaseModel
import retrofit2.Response
import retrofit2.http.GET

interface NetworkManager {

    // TODO: 20.02.2024 get all characters
    @GET("/api/character")
    suspend fun gelAllCharacters(): Response<CharacterBaseModel>
}