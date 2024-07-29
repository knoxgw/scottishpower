package com.example.scottishpower.util

inline fun <T, R : Comparable<R>> Iterable<T>.sortedBy(
    ascending: Boolean,
    crossinline selector: (T) -> R?
): List<T> {
    return sortedWith(if (ascending) compareBy(selector) else compareByDescending(selector))
}