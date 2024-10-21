package com.example.chatapp.presentation.screen.addUserDetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
    val fullName = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val bio = remember { mutableStateOf("") }
    val wordLimit = 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Create an account",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Invest and double your income now",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
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
                    .size(190.dp)
                    .padding(8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            // First Name
            OutlinedTextField(
                value = fullName.value,
                onValueChange = { fullName.value = it },
                label = { Text("First name") },
                modifier = Modifier
                    .padding(bottom = 16.dp, end = 30.dp)
                    .width(150.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Gray,
                    focusedBorderColor = Color.Green
                ),
                shape = RoundedCornerShape(50.dp),
            )

            // Last Name
            OutlinedTextField(
                value = fullName.value,
                onValueChange = { fullName.value = it },
                label = { Text("Last name") },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .width(150.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Gray,
                    focusedBorderColor = Color.Green
                ),
                shape = RoundedCornerShape(50.dp),
            )
        }

        // Phone Number Input
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it.take(10) },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Green
            ),
            shape = RoundedCornerShape(50.dp),
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
            label = { Text("Bio (max 60 words)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .height(120.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Green
            ),
            shape = RoundedCornerShape(20.dp),
            maxLines = 5,
        )

        GradientButton(
            onClick = {
                if (phoneNumber.value.length != 10) {
                } else {
                    // TODO: Proceed with account creation (Firebase authentication, etc.)
                }
            },
            text = "Create Account"
        )
    }
}


@Composable
@Preview(showBackground = true)
fun previewAddUserDetails(){
    val navController = rememberNavController()
    AddUserScreen(navController = navController)
}
