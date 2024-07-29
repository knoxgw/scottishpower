package com.example.scottishpower.domain

import com.example.scottishpower.data.entity.AlbumEntity
import com.example.scottishpower.data.repositories.PlaceholderRepository
import com.example.scottishpower.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

// todo this needs error handling - what if album or user lists empty etc
// this results in ~200 API calls and causes some overhead unfortunately
// I think this is the result of the API being slightly slow/throttled but this should be investigated
// potential improvement could be trickling in results rather than awaiting complete response
class GetAllAlbumsUseCase @Inject constructor(
    private val placeholderRepository: PlaceholderRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): List<AlbumEntity> = withContext(dispatcher) {
        placeholderRepository.getAllAlbums().map { album ->
                AlbumEntity(
                    album.id,
                    album.title,
                    placeholderRepository.getUserById(album.userId.toString()).first().username,
                    placeholderRepository.getAlbumPhotosById(album.id.toString())
                        .first().thumbnailUrl
                )
        }
    }
}