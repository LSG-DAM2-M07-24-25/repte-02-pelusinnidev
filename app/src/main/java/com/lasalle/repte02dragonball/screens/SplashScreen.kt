package com.lasalle.repte02dragonball.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
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

@Composable
fun SplashScreen(navController: NavHostController, viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo de Dragon Ball
        Image(
            painter = painterResource(id = R.drawable.dragonball_daima_logo),
            contentDescription = "Dragon Ball Logo",
            modifier = Modifier.size(400.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Botó d'entrada
        Button(
            onClick = { navController.navigate(Screens.CharacterSelection.route) }
        ) {
            Text(text = "Entrar")
        }
    }
} 