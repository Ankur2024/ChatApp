package com.example.chatapp.di

import android.content.Context
import com.example.chatapp.data.repository.addUserDetailRepositoryImpl.AddUserDetailRepositoryImpl
import com.example.chatapp.data.repository.authRepositoryImpl.AuthRepositoryImpl
import com.example.chatapp.domain.repository.addUserDetailRepository.AddUserDetailRepository
import com.example.chatapp.domain.repository.authRepository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindAddUserDetailRepository(addUserDetailRepositoryImpl: AddUserDetailRepositoryImpl): AddUserDetailRepository


}



