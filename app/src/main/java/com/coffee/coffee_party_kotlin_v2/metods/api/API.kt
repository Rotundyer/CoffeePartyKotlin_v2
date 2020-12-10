package com.coffee.coffee_party_kotlin_v2.metods.api

import android.util.Log
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

fun getApiClient(BASE_URL: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getList(URL: String, token: String, offset: Int): Call<Coffee> {
    return getApiClient(URL).create(ApiInterface::class.java)
        .getAPI(token, offset)
}

fun getTypes(URL: String, token: String): Call<Types> {
    return getApiClient(URL).create(ApiInterface::class.java)
        .getType(token)
}

interface ApiInterface {
    @GET("objects/") // ?limit=16 // ?offset=0
    fun getAPI(
        @Header("Authorization") token: String,
        @Query("offset") offset: Int
    ): Call<Coffee>

    @GET("objecttypes/")
    fun getType(
        @Header("Authorization") token: String,
    ): Call<Types>
}