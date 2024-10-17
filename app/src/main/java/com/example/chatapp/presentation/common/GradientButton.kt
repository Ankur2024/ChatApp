package com.example.chateaseapp.presentation.screen.common

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GradientButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    iconResId: Int? = null
) {
    val customGreen = Color(0xFF448976)
    val customLightGreen = Color(0xFF95EDC5)

    Button(
        onClick = onClick,
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(customGreen, customLightGreen),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (iconResId != null) Arrangement.Start else Arrangement.Center
        ) {
            iconResId?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 8.dp),
                    tint = Color.Unspecified
                )
            }
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = text,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}
