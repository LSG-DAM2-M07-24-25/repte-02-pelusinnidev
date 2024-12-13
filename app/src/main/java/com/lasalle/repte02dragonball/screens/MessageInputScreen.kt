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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lasalle.repte02dragonball.R
import com.lasalle.repte02dragonball.navigation.Screens
import com.lasalle.repte02dragonball.viewmodel.MainViewModel

@Composable
fun MessageInputScreen(
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
        
        OutlinedTextField(
            value = viewModel.userMessage.value,
            onValueChange = { viewModel.setUserMessage(it) },
            label = { Text("Escriu el teu Nom", fontSize = 16.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 180.dp)
                .height(100.dp),
            textStyle = TextStyle(fontSize = 18.sp)
        )
        
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate(Screens.FinalScreen.route) },
            enabled = viewModel.userMessage.value.isNotBlank(),
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Mostrar",
                fontSize = 18.sp
            )
        }
    }
} 