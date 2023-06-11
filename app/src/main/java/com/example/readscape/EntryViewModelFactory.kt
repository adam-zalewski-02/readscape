package com.example.readscape

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.readscape.model.BookRepository
import com.example.readscape.model.user.UserDao
import com.example.readscape.ui.EntryViewModel

class EntryViewModelFactory(private val userDao: UserDao, private val bookRepository: BookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EntryViewModel(userDao, bookRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}