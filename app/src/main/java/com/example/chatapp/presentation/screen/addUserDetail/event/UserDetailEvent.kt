package com.example.chatapp.presentation.screen.addUserDetail.event

import android.net.Uri

sealed class UserDetailEvent {
    data class FirstNameChanged(val firstName: String) : UserDetailEvent()
    data class LastNameChanged(val lastName: String) : UserDetailEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : UserDetailEvent()
    data class BioChanged(val bio: String) : UserDetailEvent()
    data class ProfileImageSelected(val uri: Uri) : UserDetailEvent()
    object Submit : UserDetailEvent()
}