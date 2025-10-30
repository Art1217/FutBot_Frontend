package com.example.futbot.data.remote


import com.example.futbot.data.model.LoginResponse
import com.example.futbot.data.model.Usuario
import retrofit2.Response

class UsuarioRepository {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    suspend fun registrarUsuario(usuario: Usuario): Response<Usuario> {
        return api.crearUsuario(usuario)
    }

    suspend fun loginUsuario(correo: String, contrasena: String): Response<LoginResponse> {
        val body = mapOf("correo" to correo, "contrasena" to contrasena)
        return api.login(body)
    }
}
