package com.example.assignement_composebase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "carousel_images")
data class CarouselImage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUrl: String,
    val title: String,
    val subtitle: String
)