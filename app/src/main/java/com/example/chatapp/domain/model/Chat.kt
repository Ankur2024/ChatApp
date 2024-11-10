package com.example.chatapp.domain.model

data class Chat(
    val chatId: String = "",
    val participants: List<String> = listOf(),
    val lastMessage: String = "",
    val lastMessageTimestamp: Long = System.currentTimeMillis(),
    val isGroupChat: Boolean = false
)

