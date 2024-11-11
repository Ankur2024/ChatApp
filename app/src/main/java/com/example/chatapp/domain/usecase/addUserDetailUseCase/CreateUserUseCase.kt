package com.example.chatapp.domain.usecase.addUserDetailUseCase

import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.repository.addUserDetailRepository.AddUserDetailRepository
import com.example.chatapp.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val repository: AddUserDetailRepository
) {
    suspend operator fun invoke(user: User): Flow<ResultState<String>> {
        return repository.createUser(user)
    }
}

