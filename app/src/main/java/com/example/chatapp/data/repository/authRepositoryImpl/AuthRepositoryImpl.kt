package com.example.chatapp.data.repository.authRepositoryImpl

import com.example.chatapp.domain.repository.authRepository.AuthRepository
import com.example.chatapp.domain.utils.ResultState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {
    override fun loginOrRegisterWithEmail(email: String): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Idle)

        val password = "yourRandomPassword"

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid
                    trySend(ResultState.Success(userId ?: "User ID is null"))
                } else {
                    val exception = task.exception
                    trySend(ResultState.Failure(exception ?: Exception("Unknown error")))
                }
            }
            .addOnFailureListener { exception ->
                trySend(ResultState.Failure(exception))
            }

        awaitClose { close() }
    }


    override fun signInWithGoogle(idToken: String): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override fun sendVerificationEmail(email: String): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override fun verifyEmailLink(email: String, emailLink: String): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }
}