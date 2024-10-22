package com.example.chatapp.presentation.screen.login.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.R
import com.example.chatapp.domain.utils.ResultState
import com.example.chatapp.presentation.common.ShowToast
import com.example.chatapp.presentation.navigation.Screen
import com.example.chatapp.presentation.screen.login.authViewmodel.AuthViewModel
import com.example.chateaseapp.presentation.screen.common.GradientButton

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val authState = viewModel.authState.collectAsState()
    val showToastMessage = remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.Black),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Login or Sign Up",
                        fontSize = 25.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Enter your email",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    LoginTextField(
                        label = "Email",
                        value = email.value,
                        onValueChange = { email.value = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    GradientButton(
                        onClick = {
                            viewModel.loginOrRegisterWithEmail(email.value)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        text = "Continue"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                        Text(
                            text = "or",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 8.dp),
                            color = Color.White
                        )
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    GradientButton(
                        onClick = {
                            // Trigger Google sign-in here
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        iconResId = R.drawable.google_icon,
                        text = stringResource(R.string.get_started)
                    )
                }
            }
        }
    }

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is ResultState.Success -> {
                val message = (authState.value as ResultState.Success).data
                showToastMessage.value = message
                if (message.contains("Verification email sent")) {
                    viewModel.verifyAndNavigate {
                        navController.navigate(Screen.add_user_detail_screen.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                }
            }
            is ResultState.Failure -> {
                val errorMessage = (authState.value as ResultState.Failure).exception.message ?: "Unknown error occurred"
                showToastMessage.value = errorMessage
            }
            else -> Unit
        }
    }


    showToastMessage.value?.let { message ->
        ShowToast(message = message, duration = Toast.LENGTH_LONG)
        showToastMessage.value = null
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun LoginTextField(
    label: String = "",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .background(
                color = Color(0xFF2C2C2C),
                shape = RoundedCornerShape(20.dp)
            )
            .shadow(10.dp, shape = RoundedCornerShape(20.dp))
            .padding(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color(0xFFD3D3D3),
            focusedTextColor = Color(0xFFD3D3D3),
            unfocusedTextColor = Color(0xFFD3D3D3)
        )
    )
}

@Composable
@Preview(showBackground = true)
fun LoginPreview(){
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
