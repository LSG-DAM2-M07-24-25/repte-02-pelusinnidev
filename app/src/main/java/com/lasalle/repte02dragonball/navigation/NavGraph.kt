package com.lasalle.repte02dragonball.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lasalle.repte02dragonball.screens.CharacterSelectionScreen
import com.lasalle.repte02dragonball.screens.FinalScreen
import com.lasalle.repte02dragonball.screens.MessageInputScreen
import com.lasalle.repte02dragonball.screens.SplashScreen
import com.lasalle.repte02dragonball.viewmodel.MainViewModel

// Definició de les rutes de navegació
sealed class Screens(val route: String) {
    object Splash : Screens("splash_screen")
    object CharacterSelection : Screens("character_selection")
    object MessageInput : Screens("message_input")
    object FinalScreen : Screens("final_screen")
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.CharacterSelection.route) {
            CharacterSelectionScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.MessageInput.route) {
            MessageInputScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.FinalScreen.route) {
            FinalScreen(navController = navController, viewModel = viewModel)
        }
    }
} 