package com.example.flashcardquicapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardquicapp.data.Flashcard
import com.example.flashcardquicapp.data.FlashcardDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlashcardViewModel(private val dao: FlashcardDao) : ViewModel() {

    private val _flashcards = dao.getAllFlashcards().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val flashcards: StateFlow<List<Flashcard>> = _flashcards

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex

    private val _isFlipped = MutableStateFlow(false)
    val isFlipped: StateFlow<Boolean> = _isFlipped

    fun nextCard() {
        _isFlipped.value = false
        _currentIndex.value = (_currentIndex.value + 1) % (_flashcards.value.size.coerceAtLeast(1))
    }

    fun previousCard() {
        _isFlipped.value = false
        _currentIndex.value = (_currentIndex.value - 1 + _flashcards.value.size) % (_flashcards.value.size.coerceAtLeast(1))
    }

    fun toggleFlip() {
        _isFlipped.value = !_isFlipped.value
    }

    fun addFlashcard(question: String, answer: String) {
        viewModelScope.launch {
            dao.insertFlashcard(Flashcard(question = question, answer = answer))
        }
    }

    fun updateFlashcard(flashcard: Flashcard) {
        viewModelScope.launch {
            dao.updateFlashcard(flashcard)
        }
    }

    fun deleteFlashcard(flashcard: Flashcard) {
        viewModelScope.launch {
            dao.deleteFlashcard(flashcard)
        }
    }
}
