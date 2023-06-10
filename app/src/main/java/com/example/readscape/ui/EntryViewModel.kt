package com.example.readscape.ui

import androidx.lifecycle.ViewModel
import com.example.readscape.data.EntryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EntryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EntryUiState())
    val uiState: StateFlow<EntryUiState> = _uiState.asStateFlow()

    fun logIn(email: String, password: String) {
        if (email == "aa" && password == "shen") {
            setLoggedIn(true)
        }
    }

    fun signUp() {
        /* TODO */
    }

    fun logOut() {
        setLoggedIn(false)
    }

    fun setEmail(email: String) {
        _uiState.update { currentState -> currentState.copy(email = email) }
    }

    fun setPassword(password: String) {
        _uiState.update { currentState -> currentState.copy(password = password) }
    }

    private fun setLoggedIn(loggedIn: Boolean) {
        _uiState.update { currentState -> currentState.copy(loggedIn = loggedIn) }
    }
}
