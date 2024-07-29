package com.example.scottishpower.data.repositories

import android.util.Log
import com.example.scottishpower.data.dto.AlbumDTO
import com.example.scottishpower.data.repositories.api.PlaceholderApi
import retrofit2.Call
import javax.inject.Inject

// todo better exception handling here
class PlaceholderRepository @Inject constructor(private val api: PlaceholderApi) {
    suspend fun getAllAlbums(): List<AlbumDTO> {
        return executeCall(api.getAllAlbums())
    }

    private suspend fun <T> executeCall(call: Call<out T>): T {
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