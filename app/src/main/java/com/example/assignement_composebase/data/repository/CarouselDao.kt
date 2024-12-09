package com.example.assignement_composebase.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.assignement_composebase.data.model.CarouselImage


@Dao
interface CarouselDao {
    @Insert
    suspend fun insertImages(images: List<CarouselImage>)

    @Query("SELECT * FROM carousel_images")
    suspend fun getImages(): List<CarouselImage>
}