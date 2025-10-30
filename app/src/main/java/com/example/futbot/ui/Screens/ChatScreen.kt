package com.example.futbot.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.futbot.R
import com.example.futbot.data.model.ChatMessage
import com.example.futbot.ui.viewmodel.ChatViewModel
import com.example.futbot.ui.viewmodel.ChatViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.reversed


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,usuarioId: Int,
    viewModel: ChatViewModel = viewModel(factory = ChatViewModelFactory(usuarioId))
) {
    val messages by viewModel.messages.collectAsState()
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(6.dp, shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4A90E2),
                    titleContentColor = Color.White
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "FutBot",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        val imageLogin = painterResource(id = R.drawable.carita2)
                        Image(
                            painter = imageLogin,
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.size(45.dp)
                        )
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 6.dp,
                shadowElevation = 8.dp,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = Color(0xFFF8F8F8)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F0F0))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        placeholder = { Text("Escribe un mensaje...", color = Color.Black) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                            .heightIn(min = 48.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (inputText.isNotBlank()) {
                                    viewModel.sendMessage(inputText)
                                    inputText = ""
                                }
                            }
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    Button(
                        onClick = {
                            if (inputText.isNotBlank()) {
                                viewModel.sendMessage(inputText)
                                inputText = ""
                            }
                        },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1565C0),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .size(height = 48.dp, width = 90.dp)
                    ) {
                        Text("Enviar")
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { msg ->
                ChatBubble(message = msg)
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val bubbleColor = if (message.isUser) Color(0xFF003366) else Color(0xFF4A90E2)
    val alignment = if (message.isUser) Alignment.End else Alignment.Start

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Surface(
            color = bubbleColor,
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 2.dp,
            modifier = Modifier
                .widthIn(max = 280.dp)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = message.content,
                modifier = Modifier.padding(10.dp),
                color = Color.White
            )
        }
    }
}
