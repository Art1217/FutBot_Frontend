package com.example.futbot.ui.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futbot.R
import com.example.futbot.ui.viewmodel.Loginviewmodel


@Composable
fun login(navController: NavHostController, viewModel: Loginviewmodel = viewModel()){

    if (viewModel.loginExitoso) {
        LaunchedEffect(Unit) {
            navController.navigate("chat") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,modifier = Modifier.fillMaxSize().background(Color.White). padding(20.dp)) {
        val image_login = painterResource(id = R.drawable.login2)
        Image(
            painter =image_login,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.size(240.dp))

        Spacer(Modifier.padding(20.dp))

        textFieldWithError(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            placeholder = "Email",
            error = viewModel.emailError,
            showError = viewModel.showErrors
        )
        Spacer(Modifier.padding(20.dp))

        textFieldWithError(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = "Contraseña",
            error = viewModel.passwordError,
            showError = viewModel.showErrors,
            visualTransformation = PasswordVisualTransformation()
        )
        val image_gmail = painterResource(id = R.drawable.gmail)

        Spacer(Modifier.padding(20.dp))

        Image(
            painter =image_gmail,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.size(30.dp))

        Spacer(Modifier.padding(30.dp))

        Button(
            onClick = {
                viewModel.loginUsuario()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF040A7E),
                contentColor = Color.White
            )
        ) {
            Text("Acceder", fontSize = 17.sp)
        }
        if (viewModel.cargando) {
            Text("Iniciando sesión...", color = Color.Gray)
        }

        viewModel.mensajeError?.let {
            Text(it, color = Color.Red)
        }
    }

}



@Composable
fun textFieldWithError(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    error: String,
    showError: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF1F3FF)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            visualTransformation = visualTransformation
        )
        if (showError && error.isNotEmpty()) {
            Text(text = error, color = Color.Red, fontSize = 12.sp)
        }
    }
}




@Composable
@Preview
fun previewlogin(){
    val navController = rememberNavController()
    login(navController)
}
