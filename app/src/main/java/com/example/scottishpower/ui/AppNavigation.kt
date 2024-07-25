package com.example.scottishpower.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.scottishpower.MainActivity
import com.example.scottishpower.ui.album.ALBUM_ROUTE
import com.example.scottishpower.ui.album.albumScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    activity: MainActivity
) {
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = ALBUM_ROUTE,
        ) {
            albumScreen()
        }
    }
}