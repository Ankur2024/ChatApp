package com.example.chatapp.presentation.screen.addUserDetail.components

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.example.chatapp.R
import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.utils.ResultState
import com.example.chatapp.presentation.screen.addUserDetail.event.UserDetailEvent
import com.example.chatapp.presentation.screen.addUserDetail.viewmodel.UserDetailViewModel
import com.example.chateaseapp.presentation.screen.common.GradientButton
import kotlinx.coroutines.launch

@Composable
fun AddUserScreen(
    navController: NavController,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val user = remember { mutableStateOf(User()) }
    val wordLimit = 60
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var showPermissionDeniedDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val createUserState by viewModel.createUserState.collectAsState()
    val defaultImageUri = getDefaultImageUri(context)

    LaunchedEffect(createUserState) {
        if (createUserState is ResultState.Success) {
            navController.navigate("home") {
                popUpTo("add_user") { inclusive = true }
            }
        }
    }

    val pickImageLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            viewModel.onEvent(UserDetailEvent.ProfileImageSelected(it))
        }
    }

    val galleryPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            pickImageLauncher.launch("image/*")
        } else {
            Toast.makeText(context, "Permission is Denied", Toast.LENGTH_SHORT).show()
            showPermissionDeniedDialog = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create an account",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )

//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 16.dp)
//                .clickable {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                        if (context.checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
//                            pickImageLauncher.launch("image/*")
//                        } else {
//                            galleryPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
//                        }
//                    } else {
//                        if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                            pickImageLauncher.launch("image/*")
//                        } else {
//                            galleryPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//                        }
//                    }
//                },
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                painter = selectedImageUri?.let { rememberAsyncImagePainter(it) }
//                    ?: rememberAsyncImagePainter(R.drawable.smallimage),
//                contentDescription = "Profile Picture",
//                modifier = Modifier
//                    .size(150.dp)
//                    .padding(8.dp)
//            )
//        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = user.value.firstName,
                onValueChange = { user.value = user.value.copy(firstName = it) },
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

            OutlinedTextField(
                value = user.value.lastName,
                onValueChange = { user.value = user.value.copy(lastName = it) },
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

            OutlinedTextField(
                value = user.value.phoneNumber,
                onValueChange = { user.value = user.value.copy(phoneNumber = it.take(10)) },
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

            OutlinedTextField(
                value = user.value.bio,
                onValueChange = {
                    val wordCount = it.split("\\s+".toRegex()).size
                    if (wordCount <= wordLimit) {
                        user.value = user.value.copy(bio = it)
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
                    if (user.value.phoneNumber.length != 10) {
                        Toast.makeText(context, "Please enter a valid 10-digit phone number.", Toast.LENGTH_SHORT).show()
                    } else {
                        val imageUri = selectedImageUri ?: defaultImageUri
                        viewModel.onEvent(UserDetailEvent.ProfileImageSelected(imageUri))
                        viewModel.onEvent(UserDetailEvent.Submit)
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

    if (showPermissionDeniedDialog) {
        PermissionDeniedDialog(
            onDismiss = { showPermissionDeniedDialog = false },
            onGoToSettings = {
                coroutineScope.launch {
                    context.startActivity(
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", context.packageName, null)
                        )
                    )
                }
            }
        )
    }
}

@Composable
fun PermissionDeniedDialog(
    onDismiss: () -> Unit,
    onGoToSettings: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Permission Required") },
        text = { Text("Storage permission is required to select a profile image. Please enable it in app settings.") },
        confirmButton = {
            Button(onClick = {
                onGoToSettings()
                onDismiss()
            }) {
                Text("Go to Settings")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Dismiss")
            }
        }
    )
}

fun getDefaultImageUri(context: Context): Uri {
    return Uri.parse("android.resource://${context.packageName}/drawable/smallimage")
}

@Composable
@Preview(showBackground = true)
fun PreviewAddUserDetails() {
    val navController = rememberNavController()
    AddUserScreen(navController = navController)
}
