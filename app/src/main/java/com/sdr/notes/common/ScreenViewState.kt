package com.sdr.notes.common

sealed class ScreenViewState<out T> {
    object Loading : ScreenViewState<Nothing>()
    data class Succes<T>(val data: T) : ScreenViewState<T>()
    data class Error(val message: String?):ScreenViewState<Nothing>()
}