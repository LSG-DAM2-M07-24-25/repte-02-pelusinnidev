package com.lasalle.repte02dragonball.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lasalle.repte02dragonball.R
import com.lasalle.repte02dragonball.navigation.Screens
import com.lasalle.repte02dragonball.viewmodel.MainViewModel
import getCharacterImage

@Composable
fun FinalScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.dragonball_daima_logo),
            contentDescription = "Dragon Ball Logo",
            modifier = Modifier.size(100.dp)
        )
        
        // Nom del personatge
        Text(
            text = viewModel.characterName.value,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        // Imatge del personatge
        viewModel.selectedCharacter.value?.let { characterId ->
            Image(
                painter = painterResource(id = getCharacterImage(characterId)),
                contentDescription = "Personatge seleccionat",
                modifier = Modifier.size(200.dp)
            )
        }
        
        // Missatge
        Text(
            text = viewModel.userMessage.value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
        
        // Botó tornar
        Button(
            onClick = { 
                viewModel.clearStates()
                navController.navigate(Screens.CharacterSelection.route) 
            }
        ) {
            Text(text = "Tornar a la selecció")
        }
    }
} 