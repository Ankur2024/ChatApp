package com.example.chateaseapp.di

import com.example.chateaseapp.data.repository.LoginRepositoryImpl
import com.example.chateaseapp.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    abstract fun bindAuthRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule{

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestoreDb(): FirebaseFirestore = FirebaseFirestore.getInstance()
}
