package com.example.scottishpower.di

import com.example.scottishpower.data.repositories.PlaceholderRepository
import com.example.scottishpower.domain.AlbumUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PlaceholderModule {
    @Binds
    @Singleton
    abstract fun bindPlaceholderRepository(placeholderRepository: PlaceholderRepository): AlbumUseCase


}