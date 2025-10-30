package com.example.futbot.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChatRetrofitClient {
    private const val BASE_URL = "http://143.110.130.31:5005/"

    val api: ChatApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApiService::class.java)
    }
}