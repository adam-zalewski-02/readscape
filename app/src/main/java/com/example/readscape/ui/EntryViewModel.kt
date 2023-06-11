package com.example.readscape.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readscape.data.EntryUiState
import com.example.readscape.model.BookRepository
import com.example.readscape.model.book.Volume
import com.example.readscape.model.user.User
import com.example.readscape.model.user.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EntryViewModel(private val userDao: UserDao, private val bookRepository: BookRepository, private val userPreferences: UserPreferences) : ViewModel() {

    private val _uiState = MutableStateFlow(EntryUiState())
    val uiState: StateFlow<EntryUiState> = _uiState.asStateFlow()

    private val _loginStatus = MutableStateFlow(false)
    val loginStatus: StateFlow<Boolean> = _loginStatus.asStateFlow()

    private val _books = MutableStateFlow<List<Volume>>(emptyList())
    val books: StateFlow<List<Volume>> = _books.asStateFlow()

    private val _loggedInUser = MutableStateFlow<User?>(null)
    val loggedInUser: StateFlow<User?> = _loggedInUser.asStateFlow()

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            if (user != null && user.password == password) {
                _loginStatus.value = true
                _loggedInUser.value = user
                userPreferences.setLoggedInStatus(true)
                userPreferences.setLoggedUserId(user.id)
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
        _loggedInUser.value = null
        _loginStatus.value = false
        userPreferences.setLoggedInStatus(false)
        userPreferences.setLoggedUserId(0)
    }

    init {
        loadUser()
    }

    fun loadUser() {
        val userId = userPreferences.getLoggedUserId()
        if (userId != 0) {
            viewModelScope.launch {
                val user = userDao.getUserById(userId) // You need to implement this function in UserDao
                if (user != null) {
                    _loggedInUser.value = user
                    _loginStatus.value = true
                }
            }
        }
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

    fun fetchBooks() {
        viewModelScope.launch {
            val books = bookRepository.getAllVolumes()
            _books.value = books
        }
    }

}
