package com.example.readscape

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.readscape.model.UserDao
import com.example.readscape.ui.EntryViewModel
import com.example.readscape.ui.UserPreferences

class EntryViewModelFactory(private val userDao: UserDao, private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EntryViewModel(userDao, userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}