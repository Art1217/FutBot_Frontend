package com.example.futbot.ui.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.futbot.data.model.ChatMessage
import com.example.futbot.data.model.ChatRequest
import com.example.futbot.data.remote.ChatRetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(private val usuarioId: Int) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        // Agregar mensaje del usuario a la UI
        _messages.value = _messages.value + ChatMessage("Usuario", text, true)

        viewModelScope.launch {
            try {
                // Preparar el request
                val request = ChatRequest(
                    usuario_Id = usuarioId, // entero
                    message = text
                )

                // Log del request
                Log.d("ChatViewModel", "Enviando mensaje -> usuario_Id: ${request.usuario_Id}, message: ${request.message}")

                // Enviar mensaje al backend
                val response = ChatRetrofitClient.api.sendMessage(request)

                // Log de la respuesta
                Log.d("ChatViewModel", "Respuesta recibida del bot: $response")

                // Convertir la respuesta en mensajes de UI
                val botMessages = response.map { ChatMessage("FutBot", it.text, false) }
                _messages.value = _messages.value + botMessages

            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error al enviar mensaje: ${e.message}")
                _messages.value = _messages.value + ChatMessage("FutBot", "Error al conectar con el servidor.", false)
            }
        }
    }
}

// ChatViewModelFactory.kt
class ChatViewModelFactory(private val usuarioId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(usuarioId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}