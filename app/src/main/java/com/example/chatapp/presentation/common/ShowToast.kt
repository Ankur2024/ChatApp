package com.example.chatapp.presentation.common

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(message: String, duration: Int = Toast.LENGTH_SHORT){
    val context = LocalContext.current
    LaunchedEffect(message) {
        Toast.makeText(context, message, duration).show()
    }
}