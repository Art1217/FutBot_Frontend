package com.example.futbot.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var nombres by mutableStateOf("")
    var apellidos by mutableStateOf("")
    var correo by mutableStateOf("")
    var contrasena by mutableStateOf("")

    var errorNombres by mutableStateOf("")
    var errorApellidos by mutableStateOf("")
    var errorCorreo by mutableStateOf("")
    var errorContrasena by mutableStateOf("")

    var showErrors by mutableStateOf(false)

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
}