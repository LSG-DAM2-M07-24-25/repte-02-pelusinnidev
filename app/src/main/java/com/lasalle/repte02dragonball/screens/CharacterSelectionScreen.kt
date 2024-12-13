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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lasalle.repte02dragonball.R
import com.lasalle.repte02dragonball.navigation.Screens
import com.lasalle.repte02dragonball.screens.components.CharacterItem
import com.lasalle.repte02dragonball.viewmodel.MainViewModel

@Composable
fun CharacterSelectionScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo més petit a dalt
        Image(
            painter = painterResource(id = R.drawable.dragonball_daima_logo),
            contentDescription = "Dragon Ball Logo",
            modifier = Modifier.size(100.dp)
        )
        
        // Grid de personatges
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(6) { index ->
                CharacterItem(
                    characterId = index,
                    isSelected = viewModel.selectedCharacter.value == index,
                    onSelect = { viewModel.setSelectedCharacter(index) }
                )
            }
        }
        
        // Botó de següent
        Button(
            onClick = { navController.navigate(Screens.MessageInput.route) },
            enabled = viewModel.selectedCharacter.value != null
        ) {
            Text(text = "Següent")
        }
    }
}