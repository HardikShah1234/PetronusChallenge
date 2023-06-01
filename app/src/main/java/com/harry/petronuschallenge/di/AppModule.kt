package com.harry.petronuschallenge.di

import com.harry.petronuschallenge.repository.DeviceHolderDetailsRepository
import com.harry.petronuschallenge.repository.DeviceHolderDetailsRepositoryImpl
import com.harry.petronuschallenge.repository.DeviceHolderListRepository
import com.harry.petronuschallenge.repository.DeviceHolderListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideRepository(impl: DeviceHolderListRepositoryImpl): DeviceHolderListRepository = impl

    @Provides
    fun provideDetailsRepository(impl: DeviceHolderDetailsRepositoryImpl): DeviceHolderDetailsRepository =
        impl
}