package com.example.chatapp.domain.usecase.authUseCase

import com.example.chatapp.domain.repository.authRepository.AuthRepository
import com.example.chatapp.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckIfEmailVerifiedUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<ResultState<String>> {
        return authRepository.checkIfEmailVerified()
    }
}
