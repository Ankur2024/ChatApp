package com.example.chatapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
//    @Binds
//    @Singleton
//    abstract fun bindAuthRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}
