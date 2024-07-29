package com.example.scottishpower.domain

import com.example.scottishpower.data.dto.AlbumDTO
import com.example.scottishpower.data.repositories.PlaceholderRepository
import javax.inject.Inject

class GetAllAlbumsUseCase @Inject constructor(private val placeholderRepository: PlaceholderRepository) {
    suspend operator fun invoke(): List<AlbumDTO> {
        return placeholderRepository.getAllAlbums()
    }
}