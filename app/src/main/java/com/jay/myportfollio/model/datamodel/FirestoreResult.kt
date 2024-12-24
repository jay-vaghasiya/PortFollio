package com.jay.myportfollio.model.datamodel

sealed class FireStoreResult<out T> {
    data class Success<out T>(val data: T) : FireStoreResult<T>()
    data class Error(val exception: Exception) : FireStoreResult<Nothing>()
    object Loading : FireStoreResult<Nothing>()
}
