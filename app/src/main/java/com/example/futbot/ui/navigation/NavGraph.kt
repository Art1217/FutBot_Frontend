package com.example.futbot.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.futbot.ui.Screens.ChatScreen
import com.example.futbot.ui.Screens.login
import com.example.futbot.ui.Screens.register
import com.example.futbot.ui.Screens.welcome

@Composable
fun NavGraph() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { welcome(navController) }
        composable("login") { login(navController)}
        composable("register"){ register(navController) }
        composable("chat"){ ChatScreen(navController) }
    }
}