package com.example.scottishpower.ui.albumdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.example.scottishpower.data.entity.AlbumDetailEntity
import com.example.scottishpower.util.NavArgs
import com.example.scottishpower.util.State
import com.example.scottishpower.util.navigateWithArgs

const val ALBUM_DETAIL_ROUTE = "album_detail_route"

fun NavController.navigateToAlbumDetail(albumId: Int) {
    navigateWithArgs(ALBUM_DETAIL_ROUTE, bundleOf(NavArgs.ALBUM_ID to albumId))
}

fun NavGraphBuilder.albumDetailScreen() {
    composable(ALBUM_DETAIL_ROUTE) {

        Box(modifier = Modifier.padding(8.dp, 8.dp)) {
            val albumDetailViewModel: AlbumDetailViewModel = hiltViewModel()

            val albumDetailState by albumDetailViewModel.albumDetailState.collectAsState()

            when (albumDetailState) {
                is State.Error -> {
                    Text("Error")
                }

                is State.Loading -> {
                    Text("Loading")
                }

                is State.Success -> {
                    Column {
                        (albumDetailState as? State.Success<AlbumDetailEntity>)?.contents?.let { detail ->
                            AlbumDetailsHeader(
                                title = detail.title,
                                username = detail.username,
                                name = detail.name,
                                companyName = detail.companyName,
                                companyCatchPhrase = detail.companyCatchPhrase
                            )
                            AlbumGallery(photoUrls = detail.photoUrls)
                        }

                    }
                }
            }
        }
    }
}

@Composable
private fun AlbumDetailsHeader(
    title: String,
    username: String,
    name: String,
    companyName: String,
    companyCatchPhrase: String
) {
    Text(text = title)
    Text(text = username)
    Text(text = name)
    Text(text = companyName)
    Text(text = companyCatchPhrase)
}

@Composable
private fun AlbumGallery(photoUrls: List<String>) {
    LazyRow {
        items(photoUrls, itemContent = { AlbumGalleryItem(photoUrl = it) })
    }
}

@Composable
private fun AlbumGalleryItem(photoUrl: String) {
    AsyncImage(model = photoUrl, contentDescription = null)
}