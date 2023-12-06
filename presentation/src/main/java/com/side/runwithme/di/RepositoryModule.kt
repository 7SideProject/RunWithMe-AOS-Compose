package com.side.runwithme.di

import com.side.data.repository.UserRepositoryImpl
import com.side.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

}