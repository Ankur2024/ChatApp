package com.example.chatapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.presentation.screen.onBoardingScreen.OnBoardingScreen


@Composable
fun Navigation(
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.OnBoarding.route) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(Screen.Login.route) {

        }
    }
}