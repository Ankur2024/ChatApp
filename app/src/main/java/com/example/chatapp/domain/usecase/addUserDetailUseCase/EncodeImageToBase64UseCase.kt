package com.example.chatapp.domain.usecase.addUserDetailUseCase

import android.net.Uri
import com.example.chatapp.domain.repository.addUserDetailRepository.AddUserDetailRepository
import com.example.chatapp.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EncodeImageToBase64UseCase @Inject constructor(
    private val repository: AddUserDetailRepository
) {
    suspend operator fun invoke(imageUri: Uri): Flow<ResultState<String>> {
        return repository.encodeImageToBase64(imageUri)
    }
}
