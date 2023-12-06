package com.side.runwithme.di

import com.side.data.datasource.user.UserDataSource
import com.side.data.datasource.user.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun provideUserDataSource(
        impl: UserDataSourceImpl
    ): UserDataSource

}