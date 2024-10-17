package com.example.chatapp.domain.utils

sealed class ResultState<out T> {
    data class Success<out R>(val data: R) : ResultState<R>()
    data class Failure(val exception: Exception, val message: String? = exception.localizedMessage) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
    object Idle : ResultState<Nothing>()
}