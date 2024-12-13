/**
 * GameScreen.kt
 * 
 * This Composable function represents the main game screen where the user interacts with the
 * Hangman game. It displays the current state of the word, the hangman image based on the
 * number of failed attempts, an on-screen keyboard for letter input, and a hints system
 * that allows users to reveal a letter based on the available hints unlocked by the selected
 * difficulty level.
 */

package com.lasalle.hangman.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lasalle.hangman.Navigation.Screen
import com.lasalle.hangman.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    navController: NavController,
    difficulty: String = "EASY"
) {
    // MARK: - State Variables
    var failCount by remember { mutableStateOf(0) } // Tracks the number of failed attempts
    var gameOver by remember { mutableStateOf(false) } // Indicates if the game is over
    var guessedLetters by remember { mutableStateOf("") } // Stores the letters guessed by the user
    var hintsRemaining by remember { mutableStateOf(0) } // Number of hints the user can use
    val coroutineScope = rememberCoroutineScope() // Coroutine scope for asynchronous tasks

    // MARK: - Determine Number of Hints Based on Difficulty
    val hintsAvailable = when (difficulty.uppercase()) {
        "MEDIUM" -> 1       // 1 hint for Medium difficulty
        "HARD" -> 2         // 2 hints for Hard difficulty
        "VERY HARD" -> 3    // 3 hints for Very Hard difficulty
        else -> 0            // No hints for Very Easy and Easy difficulties
    }

    // Initialize hintsRemaining with hintsAvailable only once
    LaunchedEffect(key1 = difficulty) {
        hintsRemaining = hintsAvailable
    }

    // MARK: - Words Categorized by Difficulty
    val wordsByDifficulty = mapOf(
        "VERY EASY" to listOf("CAT", "DOG", "SUN", "CAR", "TREE", "BOX", "HAT", "PEN", "MAP", "KEY"),
        "EASY" to listOf("HOUSE", "APPLE", "SMILE", "BRAVE", "WORLD", "BEACH", "CLOUD", "DANCE", "PAINT", "MUSIC"),
        "MEDIUM" to listOf("ELEPHANT", "JAZZ", "ORANGE", "PYTHON", "KOTLIN", "RAINBOW", "WHISPER", "JOURNEY", "PUZZLE", "BREEZE"),
        "HARD" to listOf("RINOCEROS", "HIPPOPOTAMUS", "TRANQUILITY", "MYSTERIOUS", "CHALLENGE", "BUTTERFLY", "ADVENTURE", "SYMPHONY", "LABYRINTH", "ZEPHYR"),
        "VERY HARD" to listOf("ZEITGEIST", "QUINTESSENTIAL", "FLABBERGASTED", "BUNGALOWS", "XYLOPHONE", "JUXTAPOSE", "WHIMSICAL", "EPHEMERAL", "CACOPHONY", "SERENDIPITY")
    )

    // MARK: - Select a Random Word Based on Difficulty
    var currentWord by remember { 
        mutableStateOf(wordsByDifficulty[difficulty.uppercase()]?.random() ?: "KOTLIN")
    }

    // MARK: - List of Hangman Images Corresponding to failCount
    val hangmanImages = remember {
        listOf(
            R.drawable.fail1,
            R.drawable.fail2,
            R.drawable.fail3,
            R.drawable.fail4,
            R.drawable.fail5,
            R.drawable.fail6,
            R.drawable.fail7,
            R.drawable.fail8,
            R.drawable.fail9,
            R.drawable.fail10
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // MARK: - Hangman Image
            Image(
                painter = painterResource(
                    id = if (failCount < hangmanImages.size) hangmanImages[failCount] else hangmanImages.last()
                ),
                contentDescription = "Hangman Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // MARK: - Current Word Display
            Text(
                text = currentWord.map { if (guessedLetters.contains(it)) it else "_" }.joinToString(" "),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Middle Section - Game Info
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            // Attempts Left
            Text(
                text = "Attempts Left: ${10 - failCount}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = when {
                    failCount >= 7 -> Color.Red
                    failCount >= 4 -> Color(0xFFFF9800)
                    else -> Color(0xFF4CAF50)
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Hints Left
            Text(
                text = "Hints Left: $hintsRemaining",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = if (hintsRemaining > 0) Color(0xFF4CAF50) else Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Hint Button
            Button(
                onClick = {
                    val possibleLetters = currentWord.filter { !guessedLetters.contains(it) }
                    val letterToReveal = possibleLetters.randomOrNull()
                    if (letterToReveal != null && hintsRemaining > 0) {
                        guessedLetters += letterToReveal
                        hintsRemaining--
                        if (currentWord.all { guessedLetters.contains(it) }) {
                            gameOver = true
                            coroutineScope.launch {
                                delay(1500)
                                navController.navigate(Screen.End.createRoute(true, difficulty.uppercase()))
                            }
                        }
                    }
                },
                enabled = hintsRemaining > 0 && !gameOver,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp)
            ) {
                Text("Use Hint", fontSize = 16.sp)
            }

            // Restart Button
            Button(
                onClick = {
                    failCount = 0
                    guessedLetters = ""
                    gameOver = false
                    currentWord = wordsByDifficulty[difficulty.uppercase()]?.random() ?: "KOTLIN"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp)
            ) {
                Text("Restart Game", fontSize = 16.sp)
            }
        }

        // Bottom Section - Keyboard
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            // Define the keyboard rows
            val keyboard = listOf(
                "QWERTYUIOP".toList(),
                "ASDFGHJKLÑ".toList(),
                "ZXCVBNM".toList()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Iterate over each row of the keyboard
                keyboard.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Iterate over each letter in the row
                        row.forEach { letter ->
                            Surface(
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(horizontal = 2.dp),
                                color = if (guessedLetters.contains(letter))
                                    Color.Gray
                                else
                                    MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.small,
                                onClick = {
                                    // Handle letter click only if the game is not over and letter is not guessed
                                    if (!gameOver && !guessedLetters.contains(letter)) {
                                        if (!currentWord.contains(letter)) {
                                            failCount++ // Increment fail count for wrong guess
                                        }
                                        guessedLetters += letter // Add the letter to guessedLetters

                                        // Check if the game is over (win or lose condition)
                                        val isGameOver = failCount >= 10 || 
                                                         currentWord.all { guessedLetters.contains(it) }
                                        
                                        if (isGameOver) {
                                            gameOver = true // Set game over flag
                                            coroutineScope.launch {
                                                delay(1500) // Wait for a short period before navigating
                                                val hasWon = currentWord.all { guessedLetters.contains(it) } // Determine if the user has won
                                                navController.navigate(Screen.End.createRoute(hasWon, difficulty.uppercase())) // Navigate to EndScreen with result and difficulty
                                            }
                                        }
                                    }
                                },
                                enabled = !guessedLetters.contains(letter) && !gameOver // Disable button if letter is guessed or game is over
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = letter.toString(),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (guessedLetters.contains(letter))
                                            Color.LightGray
                                        else
                                            Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}