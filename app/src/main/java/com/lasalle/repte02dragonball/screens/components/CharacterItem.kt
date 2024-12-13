package com.lasalle.repte02dragonball.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import getCharacterImage

@Composable
fun CharacterItem(
    characterId: Int,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Image(
        painter = painterResource(id = getCharacterImage(characterId)),
        contentDescription = "Character $characterId",
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clip(CircleShape)
            .border(
                width = if (isSelected) 4.dp else 1.dp,
                color = if (isSelected) Color.Blue else Color.Gray,
                shape = CircleShape
            )
            .clickable(onClick = onSelect)
    )
}