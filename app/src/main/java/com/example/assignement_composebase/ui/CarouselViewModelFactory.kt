package com.example.assignement_composebase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignement_composebase.data.local.AppDatabase
import com.example.assignement_composebase.viewmodel.CarouselViewModel


class CarouselViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarouselViewModel::class.java)) {
            return CarouselViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}