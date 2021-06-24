package com.example.dogfacts_kt.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val httpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .build()
}

val dogImageRetroFit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/breeds/image/random/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
}

val dogFactRetroFit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("https://dog-facts-api.herokuapp.com/api/v1/resources/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
}