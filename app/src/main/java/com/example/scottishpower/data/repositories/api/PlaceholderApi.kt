package com.example.scottishpower.data.repositories.api

import com.example.scottishpower.data.dto.AlbumDTO
import retrofit2.Call
import retrofit2.http.GET

interface PlaceholderApi {
    @GET("albums")
    fun getAllAlbums(): Call<List<AlbumDTO>>
}