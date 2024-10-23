package com.example.chatapp

import androidx.compose.runtime.Composable
import com.example.chatapp.presentation.navigation.Navigation

@Composable
fun MyApp(
    onGoogleSignIn: () -> Unit
) {
    Navigation(onGoogleSignIn = onGoogleSignIn)
}
