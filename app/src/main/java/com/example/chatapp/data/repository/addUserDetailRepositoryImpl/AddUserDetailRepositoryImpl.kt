package com.example.chatapp.data.repository.addUserDetailRepositoryImpl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.repository.addUserDetailRepository.AddUserDetailRepository
import com.example.chatapp.domain.utils.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class AddUserDetailRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val context: Context
) : AddUserDetailRepository {

    override suspend fun createUser(user: User): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            firebaseFirestore.collection("users").document(currentUser.uid)
                .set(user)
                .addOnSuccessListener {
                    Log.d("AddUserDetail", "User data stored successfully")
                    trySend(ResultState.Success("User data is stored successfully"))
                }
                .addOnFailureListener { e ->
                    Log.d("AddUserDetail", "Error storing user data: ${e.message}")
                    trySend(ResultState.Failure(e))
                }
        } else {
            trySend(ResultState.Failure(Exception("No authenticated user")))
        }
        awaitClose { close() }
    }

    override suspend fun encodeImageToBase64(imageUri: Uri): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        val bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(imageUri))

        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, outputStream)
        val base64Image = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)

        trySend(ResultState.Success(base64Image))
        awaitClose { close() }
    }
}
