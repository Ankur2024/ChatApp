package com.example.chatapp.presentation.screen.addUserDetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.R
import com.example.chateaseapp.presentation.screen.common.GradientButton

@Composable
fun AddUserScreen(
    navController: NavController
) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val bio = remember { mutableStateOf("") }
    val wordLimit = 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Create an account",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )

        Text(
            text = "Invest and double your income now",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Profile Picture
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clickable { /* Todo upload image */ },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // First Name
            OutlinedTextField(
                value = firstName.value,
                onValueChange = { firstName.value = it },
                label = { Text("First name", color = Color.Gray) },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(0.85f),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Gray,
                    focusedBorderColor = Color.Green
                ),
                shape = RoundedCornerShape(12.dp),
            )

            // Last Name
            OutlinedTextField(
                value = lastName.value,
                onValueChange = { lastName.value = it },
                label = { Text("Last name", color = Color.Gray) },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(0.85f),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Gray,
                    focusedBorderColor = Color.Green
                ),
                shape = RoundedCornerShape(12.dp),
            )

            // Phone Number Input
            OutlinedTextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it.take(10) },
                label = { Text("Phone number", color = Color.Gray) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(0.85f),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Gray,
                    focusedBorderColor = Color.Green
                ),
                shape = RoundedCornerShape(12.dp),
            )

            // Bio Input (Text Area)
            OutlinedTextField(
                value = bio.value,
                onValueChange = {
                    val wordCount = it.split("\\s+".toRegex()).size
                    if (wordCount <= wordLimit) {
                        bio.value = it
                    }
                },
                label = { Text("Bio", color = Color.Gray) },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth(0.85f)
                    .height(150.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Gray,
                    focusedBorderColor = Color.Green
                ),
                shape = RoundedCornerShape(12.dp),
                maxLines = 5,
            )
            GradientButton(
                onClick = {
                    if (phoneNumber.value.length != 10) {
                        // Handle error
                    } else {
                        // Proceed with account creation
                    }
                },
                text = "Create Account",
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(vertical = 16.dp)
                    .height(50.dp)
            )
        }

    }
}


@Composable
@Preview(showBackground = true)
fun previewAddUserDetails(){
    val navController = rememberNavController()
    AddUserScreen(navController = navController)
}
