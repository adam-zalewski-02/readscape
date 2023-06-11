package com.example.readscape

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.readscape.model.user.UserDao
import com.example.readscape.ui.EntryViewModel

class EntryViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EntryViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}