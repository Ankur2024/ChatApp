package com.example.chatapp.domain.model

data class Contact(
    val userId: String = "",
    val contactName: String = "",
    val contactProfileImage: String = "",
    val status: String = "active",
    val isFavorite: Boolean = false,
    val lastInteractedAt: Long = System.currentTimeMillis()
)
