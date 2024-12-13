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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.dragonball_daima_logo),
            contentDescription = "Dragon Ball Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 24.dp)
        )

        Text(
            text = viewModel.characterName.value,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 28.sp
        )
        
        viewModel.selectedCharacter.value?.let { characterId ->
            Image(
                painter = painterResource(id = getCharacterImage(characterId)),
                contentDescription = "Personatge seleccionat",
                modifier = Modifier.size(250.dp)
            )
        }
        
        Text(
            text = viewModel.userMessage.value,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 54.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = { 
                viewModel.clearStates()
                navController.navigate(Screens.CharacterSelection.route) 
            },
            modifier = Modifier
                .width(320.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Tornar a Seleccionar Personatge",
                fontSize = 18.sp
            )
        }
    }
} 