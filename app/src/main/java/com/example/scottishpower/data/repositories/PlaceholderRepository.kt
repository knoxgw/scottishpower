package com.example.scottishpower.data.repositories

import android.util.Log
import com.example.scottishpower.data.dto.AlbumDTO
import com.example.scottishpower.data.repositories.api.PlaceholderApi
import com.example.scottishpower.di.IoDispatcher
import com.example.scottishpower.domain.AlbumUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

// todo better exception handling here
class PlaceholderRepository @Inject constructor(private val api: PlaceholderApi, @IoDispatcher private val dispatcher: CoroutineDispatcher): AlbumUseCase {
    override suspend fun getAllAlbums(): List<AlbumDTO> = withContext(dispatcher) {
        Log.d(TAG, "Retrieving all albums")
        val response = api.getAllAlbums().execute()

        if (!response.isSuccessful) {
            Log.w(TAG, "${response.errorBody()}")
            throw Exception("API error")
        }

        val body = response.body()

        if (body == null) {
            Log.w(TAG, "Response empty")
            throw Exception("Response empty")
        }

        body
    }

    companion object {
        private val TAG = PlaceholderRepository::class.simpleName
    }
}