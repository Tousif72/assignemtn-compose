package com.example.assignement_composebase.data.local

import com.example.assignement_composebase.data.model.CarouselImage
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignement_composebase.data.repository.CarouselDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [CarouselImage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carouselDao(): CarouselDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "carousel_db"
                ).build()

                // Insert dummy data if the database is newly created
                GlobalScope.launch {
                    if (instance.carouselDao().getImages().isEmpty()) {
                        val dummyImages = listOf(
                            CarouselImage(imageUrl = "https://via.placeholder.com/400x200?text=Image+1", title = "Title 1", subtitle = "Subtitle 1"),
                            CarouselImage(imageUrl = "https://via.placeholder.com/400x200?text=Image+2", title = "Title 2", subtitle = "Subtitle 2"),
                            CarouselImage(imageUrl = "https://via.placeholder.com/400x200?text=Image+3", title = "Title 3", subtitle = "Subtitle 3"),
                            CarouselImage(imageUrl = "https://via.placeholder.com/400x200?text=Image+4", title = "Title 4", subtitle = "Subtitle 4"),
                            CarouselImage(imageUrl = "https://via.placeholder.com/400x200?text=Image+5", title = "Title 5", subtitle = "Subtitle 5")
                        )
                        instance.carouselDao().insertImages(dummyImages)
                    }
                }

                INSTANCE = instance
                instance
            }
        }
    }
}
