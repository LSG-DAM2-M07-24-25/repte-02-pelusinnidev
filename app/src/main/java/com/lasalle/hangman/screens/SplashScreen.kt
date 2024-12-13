/**
 * SplashScreen.kt
 * 
 * This Composable function represents the Splash screen of the Hangman app. It displays the
 * app's logo and introductory text before navigating to the Home screen after a short delay.
 */

package com.lasalle.hangman.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.lasalle.hangman.R
import com.lasalle.hangman.Navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Navigate to HomeScreen after a delay of 2 seconds
    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    // Splash Screen Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                // Brush == Gradient
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color.LightGray,
                        Color.DarkGray
                    )
                )
            )
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Logo
        Box(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Hangman Logo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
        
        // App Name
        Text(
            text = "HANGMAN",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Creators' Names
        Text(
            text = "Created by\nPol Hernández & Xavier Moreno",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Institution Name
        Text(
            text = "LaSalle Gràcia",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = NavController(LocalContext.current))
}