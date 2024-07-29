package com.example.scottishpower.ui.album

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.scottishpower.data.entity.AlbumEntity
import com.example.scottishpower.domain.GetAllAlbumsUseCase
import com.example.scottishpower.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    application: Application,
    private val getAllAlbums: GetAllAlbumsUseCase
) : AndroidViewModel(application) {

    private val _albumListState = MutableStateFlow<State<List<AlbumEntity>>>(State.Loading)
    val albumListState: StateFlow<State<List<AlbumEntity>>> = _albumListState.asStateFlow()

    init {
        retrieveAlbums()
    }

    private fun retrieveAlbums() {
        viewModelScope.launch {
            try {
                _albumListState.value = State.Success(getAllAlbums.invoke())
            } catch (exception: Exception) {
                _albumListState.value = State.Error(exception.message)
            }
        }
    }

}