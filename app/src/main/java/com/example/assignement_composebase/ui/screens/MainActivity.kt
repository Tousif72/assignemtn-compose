package com.example.assignement_composebase.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.assignement_composebase.data.local.AppDatabase
import com.example.assignement_composebase.data.repository.CarouselRepository
import com.example.assignement_composebase.ui.theme.AssignementcomposeBaseTheme
import com.example.assignement_composebase.viewmodel.CarouselViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val db = AppDatabase.getDatabase(this)
            val repository = CarouselRepository(db)
            val viewModel = ViewModelProvider(
                this,
                CarouselViewModelFactory(repository)
            ).get(CarouselViewModel::class.java)

            setContent {
                MainScreen(viewModel)
            }
        }
    }
}