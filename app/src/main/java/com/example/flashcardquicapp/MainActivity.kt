package com.example.flashcardquicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.flashcardquicapp.data.FlashcardDatabase
import com.example.flashcardquicapp.ui.FlashcardScreen
import com.example.flashcardquicapp.ui.ManageFlashcardScreen
import com.example.flashcardquicapp.viewmodel.FlashcardViewModel
import com.example.flashcardquicapp.viewmodel.FlashcardViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = FlashcardDatabase.getDatabase(applicationContext).flashcardDao()
        val factory = FlashcardViewModelFactory(dao)

        setContent {
            val viewModel: FlashcardViewModel = ViewModelProvider(this, factory)[FlashcardViewModel::class.java]

            val navController = rememberNavController()

            NavHost(navController, startDestination = "main") {
                composable("main") {
                    FlashcardScreen(viewModel = viewModel, onAddClick = {
                        navController.navigate("add")
                    })
                }
                composable("add") {
                    ManageFlashcardScreen(
                        viewModel = viewModel,
                        onSave = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
