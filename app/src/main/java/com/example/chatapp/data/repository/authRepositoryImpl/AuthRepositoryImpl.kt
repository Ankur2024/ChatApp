package com.example.chatapp.data.repository.authRepositoryImpl

import com.example.chatapp.domain.repository.authRepository.AuthRepository
import com.example.chatapp.domain.utils.ResultState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override fun loginOrRegisterWithEmail(email: String): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Idle)

        val password = "yourRandomPassword"

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = firebaseAuth.currentUser
                    currentUser?.let {
                        it.sendEmailVerification().addOnCompleteListener { verificationTask ->
                            if (verificationTask.isSuccessful) {
                                trySend(ResultState.Success("Verification email sent. Please check your email."))

                                launch {
                                    checkIfEmailVerified().collect { verificationResult ->
                                        when (verificationResult) {
                                            is ResultState.Success -> {
                                                trySend(ResultState.Success("Email is verified."))
                                            }
                                            is ResultState.Failure -> {
                                                trySend(ResultState.Failure(Exception("Email is not verified yet.")))
                                            }
                                            else -> Unit
                                        }
                                    }
                                }

                            } else {
                                val exception = verificationTask.exception
                                trySend(
                                    ResultState.Failure(
                                        exception ?: Exception("Failed to send verification email.")
                                    )
                                )
                            }
                        }
                    }
                } else {
                    val exception = task.exception
                    trySend(
                        ResultState.Failure(
                            exception ?: Exception("Unknown error during registration.")
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                trySend(ResultState.Failure(exception))
            }

        awaitClose { close() }
    }

    override fun checkIfEmailVerified(): Flow<ResultState<String>> = callbackFlow {
        val user = firebaseAuth.currentUser
        user?.reload()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (user.isEmailVerified) {
                    trySend(ResultState.Success("Email is verified."))
                } else {
                    trySend(ResultState.Failure(Exception("Email is not verified.")))
                }
            } else {
                val exception = task.exception
                trySend(ResultState.Failure(exception ?: Exception("Failed to verify email.")))
            }
        }

        awaitClose { close() }
    }

    override fun signInWithGoogle(idToken: String): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }
}
