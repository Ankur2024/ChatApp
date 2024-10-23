package com.example.chatapp.presentation.screen.login.authViewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.usecase.authUseCase.CheckIfEmailVerifiedUseCase
import com.example.chatapp.domain.usecase.authUseCase.LoginOrRegisterWithEmailUseCase
import com.example.chatapp.domain.usecase.authUseCase.SignInWithGoogleUseCase
import com.example.chatapp.domain.usecase.authUseCase.VerifyGoogleSignInUseCase
import com.example.chatapp.domain.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginOrRegisterWithEmailUseCase: LoginOrRegisterWithEmailUseCase,
    private val checkIfEmailVerifiedUseCase: CheckIfEmailVerifiedUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val verifyGoogleSignInUseCase: VerifyGoogleSignInUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<ResultState<String>>(ResultState.Idle)
    val authState: StateFlow<ResultState<String>> = _authState

    private val _googleAuthState = MutableStateFlow<ResultState<String>>(ResultState.Idle)
    val googleAuthState: StateFlow<ResultState<String>> = _googleAuthState

    fun loginOrRegisterWithEmail(email: String) {
        viewModelScope.launch {
            loginOrRegisterWithEmailUseCase(email).collect {
                _authState.value = it

                when (it) {
                    is ResultState.Success -> {
                        Log.d("Auth", "Login/Register success: ${it.data}")
                    }
                    is ResultState.Failure -> {
                        Log.d("Auth", "Login/Register failed: ${it.exception.message}")
                    }
                    else -> Log.d("Auth", "Login/Register idle or loading")
                }
            }
        }
    }


    fun verifyAndNavigate(onVerified: () -> Unit) {
        viewModelScope.launch {
            checkIfEmailVerifiedUseCase().collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        _authState.value = result
                        onVerified()
                    }
                    is ResultState.Failure -> {
                        _authState.value = result
                        Log.d("Auth", "Email verification failed: ${result.exception.message}")
                    }
                    else -> {
                        // TODO: There is nothing to do
                    }
                }
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            signInWithGoogleUseCase(idToken).collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        Log.d("Auth", "Google Sign-in successful: ${result.data}")
                        _googleAuthState.value = result
                    }
                    is ResultState.Failure -> {
                        Log.d("Auth", "Google Sign-in failed: ${result.exception.message}")
                        _googleAuthState.value = result
                    }
                    else -> {
                        _googleAuthState.value = ResultState.Idle
                    }
                }
            }
        }
    }

    fun verifyGoogleSignInAndNavigate(onGoogleVerified: () -> Unit) {
        viewModelScope.launch {
            verifyGoogleSignInUseCase().collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        _googleAuthState.value = result
                        onGoogleVerified()
                    }
                    is ResultState.Failure -> {
                        _googleAuthState.value = result
                        Log.d("Auth", "Google account verification failed: ${result.exception.message}")
                    }
                    else -> {
                        // TODO: Handle any other state
                    }
                }
            }
        }
    }
}