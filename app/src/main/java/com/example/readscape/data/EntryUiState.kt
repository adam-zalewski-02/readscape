package com.example.readscape.data

data class EntryUiState(
    val loggedIn: Boolean = false,
    val email: String = "",
    val password: String= "",
    val repeatPassword: String= ""
)