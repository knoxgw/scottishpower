package com.example.scottishpower.ui.album

sealed class SortType(val ascending: Boolean) {
    class Username(ascending: Boolean): SortType(ascending)
    class Title(ascending: Boolean): SortType(ascending)
}