package com.example.assignement_composebase.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import com.example.assignement_composebase.data.model.CarouselImage
import com.example.assignement_composebase.viewmodel.CarouselViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignement_composebase.data.local.AppDatabase
import com.example.assignement_composebase.ui.CarouselViewModelFactory
import com.google.accompanist.pager.*
androidx.lifecycle.viewmodel.compose

@Composable
fun MainScreen(database: AppDatabase) {
    // Use the factory to get the ViewModel
    val viewModel: CarouselViewModel = viewModel(
        factory = CarouselViewModelFactory(database)
    )

    val images by viewModel.images.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }
    val filteredImages = viewModel.getFilteredImages(searchQuery)

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(query = searchQuery, onQueryChanged = { searchQuery = it })

        ImageCarousel(images = filteredImages)

        ItemList(images = filteredImages)

        FloatingActionButton(onClick = {
            // Show Bottom Sheet
        }) {
            Text("Stats")
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        label = { Text("Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun ImageCarousel(images: List<CarouselImage>) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = Modifier.fillMaxWidth().height(250.dp)
    ) { page ->
        Image(
            painter = rememberImagePainter(images[page].imageUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ItemList(images: List<CarouselImage>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(images) { item ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = item.title, style = MaterialTheme.typography.h6)
                Text(text = item.subtitle, style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // Pass AppDatabase instance for preview purposes
    MainScreen(database = AppDatabase.getDatabase(
        context = TODO()
    ))
}
