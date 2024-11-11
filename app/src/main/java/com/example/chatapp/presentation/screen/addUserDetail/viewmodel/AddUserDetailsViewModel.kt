package com.example.chatapp.presentation.screen.addUserDetail.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.usecase.addUserDetailUseCase.CreateUserUseCase
import com.example.chatapp.domain.usecase.addUserDetailUseCase.EncodeImageToBase64UseCase
import com.example.chatapp.domain.utils.ResultState
import com.example.chatapp.presentation.screen.addUserDetail.event.UserDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val encodeImageToBase64UseCase: EncodeImageToBase64UseCase
) : ViewModel() {

    private val _userState = MutableStateFlow(User())
    val userState: StateFlow<User> get() = _userState

    private val _createUserState = MutableStateFlow<ResultState<String>>(ResultState.Idle)
    val createUserState: StateFlow<ResultState<String>> get() = _createUserState

    private val _imageUploadState = MutableStateFlow<ResultState<String>>(ResultState.Idle)
    val imageUploadState: StateFlow<ResultState<String>> get() = _imageUploadState

    fun onEvent(event: UserDetailEvent) {
        when (event) {
            is UserDetailEvent.ProfileImageSelected -> uploadProfileImage(event.uri)
            UserDetailEvent.Submit -> createUser()
            is UserDetailEvent.BioChanged -> TODO()
            is UserDetailEvent.FirstNameChanged -> TODO()
            is UserDetailEvent.LastNameChanged -> TODO()
            is UserDetailEvent.PhoneNumberChanged -> TODO()
        }
    }

    private fun uploadProfileImage(uri: Uri) {
        viewModelScope.launch {
            encodeImageToBase64UseCase(uri).collect { result ->
                _imageUploadState.value = result
                if (result is ResultState.Success) {
                    _userState.value = _userState.value.copy(profileImage = result.data)
                }
            }
        }
    }

    private fun createUser() {
        viewModelScope.launch {
            createUserUseCase(_userState.value).collect { result ->
                _createUserState.value = result
            }
        }
    }
}
