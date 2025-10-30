package com.example.futbot.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futbot.data.remote.UsuarioRepository
import kotlinx.coroutines.launch

class Loginviewmodel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var rememberMe by mutableStateOf(false)

    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")
    var showErrors by mutableStateOf(false)

    var cargando by mutableStateOf(false)
    var loginExitoso by mutableStateOf(false)
    var mensajeError by mutableStateOf<String?>(null)

    private val repository = UsuarioRepository()
    fun validate(): Boolean {
        emailError = ""
        passwordError = ""
        var isValid = true

        if (email.isBlank()) {
            emailError = "El correo no puede estar vacío"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Correo inválido"
            isValid = false
        }

        if (password.isBlank()) {
            passwordError = "La contraseña no puede estar vacía"
            isValid = false
        }

        return isValid
    }
    fun loginUsuario() {
        if (!validate()) return

        viewModelScope.launch {
            cargando = true
            mensajeError = null
            loginExitoso = false

            try {
                val response = repository.loginUsuario(email, password)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.usuario != null) {
                        loginExitoso = true
                        println("Login exitoso: ${loginResponse.usuario.nombres}")
                    } else {
                        mensajeError = loginResponse?.message ?: "Error desconocido"
                    }
                } else {
                    mensajeError = "Credenciales incorrectas (${response.code()})"
                    println("Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                mensajeError = "Error de conexión: ${e.message}"
                println("Excepción: ${e.message}")
            } finally {
                cargando = false
            }
        }
    }
}