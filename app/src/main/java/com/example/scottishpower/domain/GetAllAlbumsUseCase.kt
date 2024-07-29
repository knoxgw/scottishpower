package com.example.scottishpower.domain

import com.example.scottishpower.data.dto.AlbumDTO
import com.example.scottishpower.data.repositories.PlaceholderRepository
import com.example.scottishpower.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllAlbumsUseCase @Inject constructor(private val placeholderRepository: PlaceholderRepository, @IoDispatcher private val dispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(): List<AlbumDTO> = withContext(dispatcher ){
        placeholderRepository.getAllAlbums()
    }
}