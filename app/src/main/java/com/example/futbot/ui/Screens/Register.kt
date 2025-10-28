package com.example.futbot.ui.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futbot.R
import com.example.futbot.ui.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

@Composable
fun register(viewModel: RegisterViewModel = viewModel()) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize(). padding(15.dp)) {
        val image_register = painterResource(id = R.drawable.login2)
        Image(
            painter =image_register,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.size(150.dp))

        Spacer(modifier = Modifier.height(20.dp))

        textFieldWithError(
            value = viewModel.nombres,
            onValueChange = { viewModel.nombres = it },
            placeholder = "Nombres",
            error = viewModel.errorNombres,
            showError = viewModel.showErrors
        )

        Spacer(modifier = Modifier.height(20.dp))

        textFieldWithError(
            value = viewModel.apellidos,
            onValueChange = { viewModel.apellidos = it },
            placeholder = "Apellidos",
            error = viewModel.errorApellidos,
            showError = viewModel.showErrors
        )
        Spacer(modifier = Modifier.height(20.dp))

        textFieldWithError(
            value = viewModel.correo,
            onValueChange = { viewModel.correo = it },
            placeholder = "Correo",
            error = viewModel.errorCorreo,
            showError = viewModel.showErrors
        )
        Spacer(modifier = Modifier.height(20.dp))

        textFieldWithError(
            value = viewModel.contrasena,
            onValueChange = { viewModel.contrasena = it },
            placeholder = "Contraseña",
            error = viewModel.errorContrasena,
            showError = viewModel.showErrors,
            visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                viewModel.showErrors = true
                if (!viewModel.validar()) return@Button
                },
            modifier = Modifier.fillMaxWidth() .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF040A7E))
        ) {
            Text("Registrarse", color = Color.White)
        }
    }
}




@Composable
@Preview
fun prevRegister(){
    register()
}