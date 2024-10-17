package com.example.chatapp.domain.model

data class Auth(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val bio: String = "",
    val profileImage: String = ""
)
