package com.example.flashcardquicapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flashcardquicapp.data.Flashcard
import com.example.flashcardquicapp.viewmodel.FlashcardViewModel

@Composable
fun ManageFlashcardScreen(
    viewModel: FlashcardViewModel,
    flashcardToEdit: Flashcard? = null,
    onSave: () -> Unit,
    onDelete: (() -> Unit)? = null
) {
    var question by remember { mutableStateOf(flashcardToEdit?.question ?: "") }
    var answer by remember { mutableStateOf(flashcardToEdit?.answer ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = if (flashcardToEdit == null) "Add Flashcard" else "Edit Flashcard",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = question,
            onValueChange = { question = it },
            label = { Text("Question") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("Answer") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (flashcardToEdit == null) {
                    viewModel.addFlashcard(question, answer)
                } else {
                    viewModel.updateFlashcard(
                        flashcardToEdit.copy(question = question, answer = answer)
                    )
                }
                onSave()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        if (flashcardToEdit != null && onDelete != null) {
            OutlinedButton(
                onClick = {
                    viewModel.deleteFlashcard(flashcardToEdit)
                    onDelete()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete")
            }
        }
    }
}
