package com.lasalle.repte02dragonball.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Estat per guardar el personatge seleccionat
    private val _selectedCharacter = mutableStateOf<Int?>(null)
    val selectedCharacter: State<Int?> = _selectedCharacter

    // Estat per guardar el missatge de l'usuari
    private val _userMessage = mutableStateOf("")
    val userMessage: State<String> = _userMessage

    // Funció per actualitzar el personatge seleccionat
    fun setSelectedCharacter(characterId: Int) {
        _selectedCharacter.value = characterId
    }

    // Funció per actualitzar el missatge
    fun setUserMessage(message: String) {
        _userMessage.value = message
    }
} 