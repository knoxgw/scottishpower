package com.example.scottishpower.domain

import com.example.scottishpower.data.dto.AlbumDTO

interface AlbumUseCase {
    suspend fun getAllAlbums(): List<AlbumDTO>
}