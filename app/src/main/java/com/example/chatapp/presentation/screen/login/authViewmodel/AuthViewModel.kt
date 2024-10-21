package com.example.chatapp.presentation.screen.login.authViewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.usecase.authUseCase.LoginOrRegisterWithEmailUseCase
import com.example.chatapp.domain.usecase.authUseCase.CheckIfEmailVerifiedUseCase
import com.example.chatapp.domain.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginOrRegisterWithEmailUseCase: LoginOrRegisterWithEmailUseCase,
    private val checkIfEmailVerifiedUseCase: CheckIfEmailVerifiedUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<ResultState<String>>(ResultState.Idle)
    val authState: StateFlow<ResultState<String>> = _authState

    fun loginOrRegisterWithEmail(email: String) {
        viewModelScope.launch {
            loginOrRegisterWithEmailUseCase(email).collect {
                _authState.value = it

                when (it) {
                    is ResultState.Success -> {
                        Log.d("Auth","Login/Register success: ${it.data}")
                    }
                    is ResultState.Failure -> {
                        Log.d("Auth","Login/Register failed: ${it.exception.message}")
                    }
                    else -> Log.d("Auth","Login/Register idle or loading")
                }
            }
        }
    }

    fun verifyAndNavigate(onVerified: () -> Unit) {
        viewModelScope.launch {
            checkIfEmailVerifiedUseCase().collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        println("Email is verified successfully")
                        _authState.value = result
                        onVerified()  // Perform navigation when verified
                    }
                    is ResultState.Failure -> {
                        println("Email verification failed: ${result.exception.message}")
                        _authState.value = result
                    }
                    else -> Unit
                }
            }
        }
    }
}

