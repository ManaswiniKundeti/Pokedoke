package com.manu.pokedoke.viewstate

sealed class ViewState<out T>

class Success<out T>(val data: T): ViewState<T>()

class Error(val errMsg: String): ViewState<Nothing>()

object Loading : ViewState<Nothing>()