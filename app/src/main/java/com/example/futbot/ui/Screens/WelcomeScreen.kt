package com.example.futbot.ui.Screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futbot.R

@Composable
fun welcome() {
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize(). padding(15.dp)) {
        val image_welcome = painterResource(id = R.drawable.login2)
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter =image_welcome,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.size(341.dp))

        Spacer(Modifier.padding(50.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth() .height(93.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF040A7E))
        ) {
            Text("Iniciar sesión", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth() .height(93.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A90E2))
        ) {
            Text("Registrarse", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold )
        }




    }



}

@Composable
@Preview
fun prevWelcome() {
    welcome()
}