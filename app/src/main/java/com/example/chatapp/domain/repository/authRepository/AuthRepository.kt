package com.example.chatapp.domain.repository.authRepository

import com.example.chatapp.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginOrRegisterWithEmail(email: String): Flow<ResultState<String>>
    fun signInWithGoogle(idToken: String): Flow<ResultState<String>>
    fun checkIfEmailVerified(): Flow<ResultState<String>>
}