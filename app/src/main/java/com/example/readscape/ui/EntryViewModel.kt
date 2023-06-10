package com.example.readscape.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readscape.data.EntryUiState
import com.example.readscape.model.User
import com.example.readscape.model.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EntryViewModel(private val userDao: UserDao) : ViewModel() {
    private val _uiState = MutableStateFlow(EntryUiState())
    val uiState: StateFlow<EntryUiState> = _uiState.asStateFlow()

    private val _loginStatus = MutableStateFlow(false)
    val loginStatus: StateFlow<Boolean> = _loginStatus.asStateFlow()

    private val _loggedInUser = MutableStateFlow<User?>(null)
    val loggedInUser: StateFlow<User?> = _loggedInUser.asStateFlow()
    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            if (user != null && user.password == password) {
                _loginStatus.value = true
                _loggedInUser.value = user
            } else {
                _loginStatus.value = false
                _loggedInUser.value = null
            }
        }
    }

    fun signUp(email: String, password: String, repeatPassword: String): Boolean {
        if (password == repeatPassword) {
            viewModelScope.launch {
                val user = User(0, email, password)
                userDao.insertUsers(user)
            }
            return true
        } else {
            return false
        }
    }
    fun logOut() {
        _loginStatus.value = false
    }

    fun setEmail(email: String) {
        _uiState.update { currentState -> currentState.copy(email = email) }
    }

    fun setPassword(password: String) {
        _uiState.update { currentState -> currentState.copy(password = password) }
    }
}
