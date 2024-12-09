package com.example.assignement_composebase.data.repository

import com.example.assignement_composebase.data.local.AppDatabase
import com.example.assignement_composebase.data.model.CarouselImage


class CarouselRepository(private val db: AppDatabase) {

    suspend fun getImages(): List<CarouselImage> {
        return db.carouselDao().getImages()
    }

    suspend fun insertImages(images: List<CarouselImage>) {
        db.carouselDao().insertImages(images)
    }
}