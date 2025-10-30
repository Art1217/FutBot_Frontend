package com.example.futbot.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(
            route = "chat/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") { type = NavType.IntType })
        ) { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getInt("usuarioId") ?: 0
            ChatScreen(navController = navController, usuarioId = usuarioId)
        }
    }
}