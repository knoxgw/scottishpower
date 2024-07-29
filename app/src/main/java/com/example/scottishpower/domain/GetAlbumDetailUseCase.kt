package com.example.scottishpower.domain

import com.example.scottishpower.data.entity.AlbumDetailEntity
import com.example.scottishpower.data.repositories.PlaceholderRepository
import com.example.scottishpower.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAlbumDetailUseCase @Inject constructor(
    private val placeholderRepository: PlaceholderRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(albumId: Int): AlbumDetailEntity = withContext(dispatcher) {
        val album = placeholderRepository.getAlbumById(albumId.toString()).first()
        val user = placeholderRepository.getUserById(album.userId.toString()).first()
        val photos = placeholderRepository.getAlbumPhotosById(albumId.toString())

        AlbumDetailEntity(albumId, album.title, user.username, user.name, user.company.name, user.company.catchPhrase, photos.map { it.url })
    }
}