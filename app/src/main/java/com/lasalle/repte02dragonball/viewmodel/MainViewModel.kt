package com.lasalle.repte02dragonball.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Estat per guardar el personatge seleccionat
    private val _selectedCharacter = mutableStateOf<Int?>(null)
    val selectedCharacter: State<Int?> = _selectedCharacter

    // Estat per guardar el nom del personatge
    private val _characterName = mutableStateOf("")
    val characterName: State<String> = _characterName

    // Estat per guardar el missatge de l'usuari
    private val _userMessage = mutableStateOf("")
    val userMessage: State<String> = _userMessage

    // Funció per actualitzar el personatge seleccionat i el seu nom
    fun setSelectedCharacter(characterId: Int) {
        _selectedCharacter.value = characterId
        _characterName.value = getCharacterName(characterId)
    }

    // Funció per actualitzar el missatge
    fun setUserMessage(message: String) {
        _userMessage.value = message
    }

    // Funció per obtenir el nom del personatge
    private fun getCharacterName(index: Int): String {
        return when(index) {
            0 -> "Goku"
            1 -> "Gohan"
            2 -> "Masked Majin"
            3 -> "Piccolo"
            4 -> "Supreme"
            5 -> "Vegeta"
            else -> "Unknown"
        }
    }

    // Afegim una funció per netejar els estats
    fun clearStates() {
        _selectedCharacter.value = null
        _characterName.value = ""
        _userMessage.value = ""
    }
} 