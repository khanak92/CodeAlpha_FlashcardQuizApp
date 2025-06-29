package com.example.flashcardquicapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashcardquicapp.data.FlashcardDao

class FlashcardViewModelFactory(private val dao: FlashcardDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlashcardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlashcardViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
