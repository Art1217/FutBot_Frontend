package com.example.futbot.data.model


data class Usuario(
    val usuarioId: Int? = null,
    val nombres: String,
    val apellido_paterno: String,
    val apellido_materno: String,
    val correo: String,
    val contrasena: String? = null
)
data class LoginRequest(
    val correo: String,
    val contrasena: String
)
data class LoginResponse(
    val message: String,
    val usuario: Usuario?
)