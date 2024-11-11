package com.example.chatapp.domain.repository.addUserDetailRepository

import android.net.Uri
import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AddUserDetailRepository {
        suspend fun createUser(user: User): Flow<ResultState<String>>
        suspend fun encodeImageToBase64(imageUri: Uri): Flow<ResultState<String>>
}
