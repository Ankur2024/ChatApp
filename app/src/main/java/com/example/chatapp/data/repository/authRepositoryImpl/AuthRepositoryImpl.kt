package com.example.chatapp.data.repository.authRepositoryImpl

import com.example.chatapp.domain.repository.authRepository.AuthRepository
import com.example.chatapp.domain.utils.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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
        if (user == null) {
            trySend(ResultState.Failure(Exception("User not found.")))
            awaitClose { close() }
            return@callbackFlow
        }

        while (true) {
            user.reload().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (user.isEmailVerified) {
                        trySend(ResultState.Success("Email is verified."))
                        close()
                    } else {
                        trySend(ResultState.Failure(Exception("Email is not verified.")))
                    }
                } else {
                    trySend(ResultState.Failure(task.exception ?: Exception("Failed to reload user.")))
                }
            }
            delay(2000)
        }
        awaitClose { close() }
    }

    override fun signInWithGoogle(idToken: String): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                trySend(ResultState.Success("Google Sign-In successful"))
            } else {
                trySend(ResultState.Failure(task.exception ?: Exception("Google Sign-In failed")))
            }
        }.addOnFailureListener { exception ->
            trySend(ResultState.Failure(exception))
        }

        awaitClose { close() }
    }

    override fun verifyGoogleSignIn(): Flow<ResultState<String>> = callbackFlow {
        val user = firebaseAuth.currentUser
        if (user == null) {
            trySend(ResultState.Failure(Exception("User not found.")))
            awaitClose { close() }
            return@callbackFlow
        }

        while (true) {
            user.reload().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (user.isEmailVerified) {
                        trySend(ResultState.Success("Google account is verified."))
                        close()
                    } else {
                        trySend(ResultState.Failure(Exception("Google account is not verified.")))
                    }
                } else {
                    trySend(ResultState.Failure(task.exception ?: Exception("Failed to reload user.")))
                }
            }
            delay(3000)
        }
        awaitClose { close() }
    }
}