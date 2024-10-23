package com.example.chatapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.presentation.screen.addUserDetail.components.AddUserScreen
import com.example.chatapp.presentation.screen.home.components.HomeScreen
import com.example.chatapp.presentation.screen.login.components.LoginScreen
import com.example.chatapp.presentation.screen.onBoardingScreen.OnBoardingScreen

@Composable
fun Navigation(
    onGoogleSignIn: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.OnBoarding.route) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, onGoogleSignIn = onGoogleSignIn)
        }
        composable(Screen.add_user_detail_screen.route) {
            AddUserScreen(navController = navController)
        }
        composable(Screen.home_screen.route) {
            HomeScreen(navController = navController)
        }
    }
}
