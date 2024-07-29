package com.example.scottishpower.ui.albumlist

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
import androidx.compose.material3.TextButton
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
import com.example.scottishpower.data.entity.AlbumEntity
import com.example.scottishpower.util.State

const val ALBUM_LIST_ROUTE = "album_list_route"

fun NavController.navigateToAlbumList() {
    navigate(ALBUM_LIST_ROUTE)
}

fun NavGraphBuilder.albumListScreen(navigateToDetail: (Int) -> Unit) {
    composable(ALBUM_LIST_ROUTE) {
        Box(modifier = Modifier.padding(8.dp, 8.dp)) {
            val albumListViewModel: AlbumListViewModel = hiltViewModel()

            val albumListState by albumListViewModel.albumListState.collectAsState()

            when (albumListState) {
                is State.Error -> {
                    Text("Error")
                }

                is State.Loading -> {
                    Text("Loading")
                }

                is State.Success -> {
                    Column {
                        SortBar {
                            albumListViewModel.setSortType(it)
                        }
                        AlbumList((albumListState as State.Success<List<AlbumEntity>>).contents, navigateToDetail)
                    }
                }
            }
        }
    }
}

@Composable
private fun SortBar(sortCallback: (SortType) -> Unit) {
    // todo icons here, indicate selected sort
    Column {
        Text(text = "Sort by:")
        Row {
            Text(text = "Username")
            TextButton(onClick = { sortCallback.invoke(SortType.Username(ascending = true)) }) {
                Text(text = "Ascending")
            }
            TextButton(onClick = { sortCallback.invoke(SortType.Username(ascending = false)) }) {
                Text(text = "Descending")
            }
        }
        Row {
            Text(text = "Album title")
            TextButton(onClick = { sortCallback.invoke(SortType.Title(ascending = true)) }) {
                Text(text = "Ascending")
            }
            TextButton(onClick = { sortCallback.invoke(SortType.Title(ascending = false)) }) {
                Text(text = "Descending")
            }
        }
    }
}

@Composable
private fun AlbumList(albums: List<AlbumEntity>, navigateToDetail: (Int) -> Unit) {
    LazyColumn {
        items(albums, itemContent = { AlbumItem(album = it, navigateToDetail) })
    }
}

@Composable
private fun AlbumItem(album: AlbumEntity, navigateToDetail: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {navigateToDetail.invoke(album.albumId)}
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