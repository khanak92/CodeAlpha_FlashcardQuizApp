package com.example.flashcardquicapp.data

import androidx.compose.runtime.Composable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.material.floatingactionbutton.FloatingActionButton


@Entity(tableName = "flashcards")
data class Flashcard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val question: String,
    val answer: String
)



