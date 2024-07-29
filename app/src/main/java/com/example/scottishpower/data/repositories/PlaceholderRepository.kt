package com.example.scottishpower.data.repositories

import android.util.Log
import com.example.scottishpower.data.dto.AlbumDTO
import com.example.scottishpower.data.dto.PhotoDTO
import com.example.scottishpower.data.dto.UserDTO
import com.example.scottishpower.data.repositories.api.PlaceholderApi
import retrofit2.Call
import javax.inject.Inject

// todo better exception handling here
class PlaceholderRepository @Inject constructor(private val api: PlaceholderApi) {
    fun getAllAlbums(): List<AlbumDTO> {
        Log.d(TAG, "Fetching all albums")
        return executeCall(api.getAllAlbums())
    }

    fun getUserById(userId: String): List<UserDTO> {
        Log.d(TAG, "Fetching user $userId")
        return executeCall(api.getUserById(userId))
    }

    fun getAlbumPhotosById(albumId: String): List<PhotoDTO> {
        Log.d(TAG, "Fetching photos for album $albumId")
        return executeCall(api.getPhotosByAlbum(albumId))
    }

    fun getAlbumById(albumId: String): List<AlbumDTO> {
        Log.d(TAG, "Fetching album $albumId")
        return executeCall(api.getAlbumById(albumId))
    }

    private fun <T> executeCall(call: Call<out T>): T {
        val response = call.execute()

        if (!response.isSuccessful) {
            Log.w(TAG, "${response.errorBody()}")
            throw Exception(response.message())
        }

        val body = response.body()

        if (body == null) {
            Log.w(TAG, "Response empty")
            throw Exception()
        }

        return body
    }

    companion object {
        private val TAG = PlaceholderRepository::class.simpleName
    }
}