package com.example.futbot.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futbot.data.model.Usuario
import com.example.futbot.data.remote.UsuarioRepository
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    // Campos del formulario
    var nombres by mutableStateOf("")
    var apellidos by mutableStateOf("")
    var correo by mutableStateOf("")
    var contrasena by mutableStateOf("")

    // Errores de validación
    var errorNombres by mutableStateOf("")
    var errorApellidos by mutableStateOf("")
    var errorCorreo by mutableStateOf("")
    var errorContrasena by mutableStateOf("")

    var showErrors by mutableStateOf(false)

    //Estado de la solicitud
    var registroExitoso by mutableStateOf(false)
    var mensajeError by mutableStateOf<String?>(null)
    var cargando by mutableStateOf(false)

    private val repository = UsuarioRepository()
    fun validar(): Boolean {
        showErrors = true
        var valido = true

        if (nombres.isBlank()) {
            errorNombres = "Campo obligatorio"
            valido = false
        } else errorNombres = ""

        if (apellidos.isBlank()) {
            errorApellidos = "Campo obligatorio"
            valido = false
        } else errorApellidos = ""

        if (correo.isBlank()) {
            errorCorreo = "Campo obligatorio"
            valido = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            errorCorreo = "Correo inválido"
            valido = false
        } else errorCorreo = ""

        if (contrasena.isBlank()) {
            errorContrasena = "Campo obligatorio"
            valido = false
        } else if (contrasena.length < 6) {
            errorContrasena = "Debe tener mínimo 6 dígitos"
            valido = false
        } else errorContrasena = ""

        return valido
    }
    //  REGISTRO EN BACKEND
    fun registrarUsuario() {
        if (!validar()) return

        viewModelScope.launch {
            cargando = true
            mensajeError = null
            registroExitoso = false

            try {
                // Separar apellidos
                val partes = apellidos.trim().split(" ")
                val apellidoPaterno = partes.getOrElse(0) { "" }
                val apellidoMaterno = partes.getOrElse(1) { "" }

                val nuevoUsuario = Usuario(
                    nombres = nombres,
                    apellido_paterno = apellidoPaterno,
                    apellido_materno = apellidoMaterno,
                    correo = correo,
                    contrasena = contrasena
                )

                val response = repository.registrarUsuario(nuevoUsuario)

                if (response.isSuccessful) {
                    registroExitoso = true
                    println("Registro exitoso: ${response.body()}")
                } else {
                    mensajeError = "Error en el registro (${response.code()})"
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