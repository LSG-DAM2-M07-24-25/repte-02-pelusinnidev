package com.example.repte02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lasalle.repte02.CharacterSelectionScreen
import com.lasalle.repte02.LaunchScreen
import com.lasalle.repte02.NameSelectionScreen
import com.lasalle.repte02.ResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                var selectedCharacter by remember { mutableStateOf("") }
                var playerName by remember { mutableStateOf("") }

                NavHost(navController = navController, startDestination = "launch_screen") {
                    composable("launch_screen") {
                        LaunchScreen(onNavigateToCharacterSelection = {
                            navController.navigate("character_selection")
                        })
                    }
                    composable("character_selection") {
                        CharacterSelectionScreen(
                            onCharacterSelected = { character ->
                                selectedCharacter = character
                                navController.navigate("name_selection")
                            }
                        )
                    }
                    composable("name_selection") {
                        NameSelectionScreen(
                            onNameSubmitted = { name ->
                                playerName = name
                                navController.navigate("result")
                            }
                        )
                    }
                    composable("result") {
                        ResultScreen(
                            characterName = selectedCharacter,
                            playerName = playerName
                        )
                    }
                }
            }
        }
    }
}