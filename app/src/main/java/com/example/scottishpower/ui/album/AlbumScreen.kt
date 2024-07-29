package com.example.scottishpower.ui.album

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.example.scottishpower.data.dto.AlbumDTO
import com.example.scottishpower.data.entity.AlbumEntity
import com.example.scottishpower.util.State

const val ALBUM_ROUTE = "album_route"

fun NavController.navigateToAlbum() {
    navigate(ALBUM_ROUTE)
}

fun NavGraphBuilder.albumScreen() {
    composable(ALBUM_ROUTE) {
        Box(modifier = Modifier.padding(8.dp, 8.dp)) {
            val albumViewModel: AlbumViewModel = hiltViewModel()

            val albumListState by albumViewModel.albumListState.collectAsState()

            when (albumListState) {
                is State.Error -> {
                    Text("Error")
                }

                State.Loading -> {
                    Text("Loading")
                }

                is State.Success -> {
                    AlbumList((albumListState as State.Success<List<AlbumEntity>>).contents)
                }
            }
        }
    }
}

@Composable
private fun AlbumList(albums: List<AlbumEntity>) {
    LazyColumn {
        items(albums, itemContent = { AlbumItem(album = it) })
    }
}

@Composable
private fun AlbumItem(album: AlbumEntity) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            AsyncImage(model = album.thumbnailUrl, contentDescription = null)
            Column {
                Text(text = album.title)
                Text(text = album.username)
            }
        }
    }
}