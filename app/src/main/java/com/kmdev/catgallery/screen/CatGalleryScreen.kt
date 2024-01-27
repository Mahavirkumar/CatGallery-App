package com.kmdev.catgallery.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.kmdev.catgallery.viewmodels.CatGalleryViewModel

@Composable
fun CatGalleryScreen() {

    val catGalleryViewModel: CatGalleryViewModel = hiltViewModel()
    val catImagesList = catGalleryViewModel.catImagesList.collectAsState(emptyList())

    if (catImagesList.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.bodySmall)
        }
    } else {

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(catImagesList.value.distinct()) {
                CatListItem(imageUrl = it.url, imgWidth = it.width, imgHeight = it.height)
            }
        }
    }

}


@Composable
fun CatListItem(imageUrl: String, imgWidth: Int?, imgHeight: Int) {
    val painter = rememberAsyncImagePainter(imageUrl)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(imgHeight.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp))
        )
    }
}

