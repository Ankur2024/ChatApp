package com.example.chatapp.presentation.screen.onBoardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.R
import com.example.chatapp.presentation.navigation.Screen
import com.example.chateaseapp.presentation.screen.common.GradientButton

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val customGreen = Color(0xFF448976)
    val customLightGreen = Color(0xFF95EDC5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .weight(0.60f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.boarding),
                contentDescription = stringResource(R.string.boarding_background),
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .weight(0.40f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.onboarding_description),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .widthIn(max = 300.dp),
                    lineHeight = 35.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                GradientButton(
                    onClick = { navController.navigate(Screen.Login.route) },
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp),
                    text = stringResource(R.string.get_started)
                )
            }
        }
    }
}




@Composable
@Preview(showBackground = true)
fun onBoardingPreview(){
    val navController = rememberNavController()
    OnBoardingScreen(navController = navController)
}