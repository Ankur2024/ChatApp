package com.example.chatapp.domain.model

data class Message(
    val messageId: String = "",
    val senderId: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val messageType: String = "text"
)


