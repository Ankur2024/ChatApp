package com.example.chatapp.domain.model

data class User(
    val userId: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val bio: String = "",
    val profileImage: String = "",
    val contacts: List<Contact> = listOf(),
    val createdAt: Long = System.currentTimeMillis()
)
