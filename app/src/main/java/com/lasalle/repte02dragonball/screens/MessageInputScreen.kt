package com.lasalle.repte02dragonball.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lasalle.repte02dragonball.R
import com.lasalle.repte02dragonball.navigation.Screens
import com.lasalle.repte02dragonball.viewmodel.MainViewModel

@Composable
fun MessageInputScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo a dalt
        Image(
            painter = painterResource(id = R.drawable.dragon_ball_logo),
            contentDescription = "Dragon Ball Logo",
            modifier = Modifier.size(100.dp)
        }
        
        // Camp de text pel missatge
        OutlinedTextField(
            value = viewModel.userMessage.value,
            onValueChange = { viewModel.setUserMessage(it) },
            label = { Text("Escriu el teu missatge") },
            modifier = Modifier.padding(16.dp)
        )
        
        // Botó de següent
        Button(
            onClick = { navController.navigate(Screens.FinalScreen.route) },
            enabled = viewModel.userMessage.value.isNotBlank()
        ) {
            Text(text = "Següent")
        }
    }
} 