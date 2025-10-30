package com.example.futbot.data.remote

import com.example.futbot.data.model.ChatRequest
import com.example.futbot.data.model.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApiService {
    @POST("webhooks/rest/webhook")
    suspend fun sendMessage(@Body message: ChatRequest): List<ChatResponse>
}