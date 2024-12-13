package com.lasalle.repte02dragonball.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lasalle.repte02dragonball.R
import com.lasalle.repte02dragonball.navigation.Screens
import com.lasalle.repte02dragonball.screens.components.CharacterItem
import com.lasalle.repte02dragonball.viewmodel.MainViewModel

@Composable
fun CharacterSelectionScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Logo més gran
        Image(
            painter = painterResource(id = R.drawable.dragonball_daima_logo),
            contentDescription = "Dragon Ball Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 24.dp)
        )
        
        // Grid més gran
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(6) { index ->
                CharacterItem(
                    characterId = index,
                    isSelected = viewModel.selectedCharacter.value == index,
                    onSelect = { viewModel.setSelectedCharacter(index) }
                )
            }
        }
        
        // Botó més gran
        Button(
            onClick = { navController.navigate(Screens.MessageInput.route) },
            enabled = viewModel.selectedCharacter.value != null,
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Continuar",
                fontSize = 18.sp
            )
        }
    }
}