package com.example.chatapp.presentation.screen.home.components.bottomNav

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatapp.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNav(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Black.copy(alpha = 0.9f))
            .padding(vertical = 7.dp, horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                iconResId = R.drawable.chat,
                label = "Chats",
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            BottomNavItem(
                iconResId = R.drawable.phone,
                label = "Calls",
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            BottomNavItem(
                iconResId = R.drawable.adaptation,
                label = "Settings",
                color = Color.White
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun BottomNavPreview() {
    BottomNav()
}
