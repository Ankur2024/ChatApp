package com.example.chatapp.di

import com.example.chatapp.data.repository.authRepositoryImpl.AuthRepositoryImpl
import com.example.chatapp.domain.repository.authRepository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule{
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}
