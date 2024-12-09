package com.example.assignement_composebase.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignement_composebase.data.local.AppDatabase
import com.example.assignement_composebase.data.model.CarouselImage
import com.example.assignement_composebase.data.repository.CarouselRepository
import kotlinx.coroutines.launch

class CarouselViewModel(private val database: AppDatabase) : ViewModel() {
    private val _images = mutableStateOf<List<CarouselImage>>(emptyList())
    val images: Thread.State<List<CarouselImage>> = _images

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            _images.value = database.carouselDao().getImages()
        }
    }

    fun getFilteredImages(query: String): List<CarouselImage> {
        return images.value.filter { it.title.contains(query, ignoreCase = true) || it.subtitle.contains(query, ignoreCase = true) }
    }
}