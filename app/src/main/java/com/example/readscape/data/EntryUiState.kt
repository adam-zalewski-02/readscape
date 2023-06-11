package com.example.readscape.data

import com.example.readscape.model.book.VolumeInfo

data class EntryUiState(
    val loggedIn: Boolean = false,
    val email: String = "",
    val password: String= "",
    val repeatPassword: String= "",
)