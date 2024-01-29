package com.kmdev.catgallery.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.kmdev.catgallery.R
import com.kmdev.catgallery.viewmodels.CatGalleryViewModel

@Composable
fun CatGalleryScreen() {

    val catGalleryViewModel: CatGalleryViewModel = hiltViewModel()
    val catImagesList = catGalleryViewModel.catImagesList.collectAsState(emptyList())

    if (catImagesList.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.bodySmall)
        }
    } else {

        LazyColumn(
            contentPadding = PaddingValues(8.dp), verticalArrangement = Arrangement.SpaceAround
        ) {
            items(catImagesList.value.distinct()) {
                CatListItem(
                    imageUrl = it.url, id = it.id
                )
            }
        }
    }

}


@Composable
fun CatListItem(imageUrl: String, id: String) {

    val colorScheme = MaterialTheme.colorScheme

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
            .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    val painterState = painter.state

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
                // Add a border and a shadow to the box
                .border(1.dp, colorScheme.primaryContainer, RoundedCornerShape(4.dp))
        ) {
            when (painterState) {
                is AsyncImagePainter.State.Success -> {
                    // Image loaded successfully, display the image
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                            .crossfade(true).build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(4.dp))
                    )
                }

                is AsyncImagePainter.State.Error -> {
                    DefaultImage()
                }

                is AsyncImagePainter.State.Loading -> {
                    // Image is still loading, show the progress spinner
                    CommonProgressSpinner()
                }

                else -> {}
            }
        }

        Text(
            text = getDummyName(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = colorScheme.secondary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, start = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ID: ${id}",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = buildAnnotatedString {
                    // Use the default style for the first part of the text
                    append("Description: ")
                    // Use a different style for the second part of the text
                    withStyle(
                        style = SpanStyle(
                            color = colorScheme.primary,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(getDummyDescription())
                    }
                },
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .widthIn(max = 310.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.Justify
            )

        }

    }
}

@Composable
fun CommonProgressSpinner() {
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun DefaultImage() {
    Image(
        painter = painterResource(id = R.drawable.outline_airplay_24),
        contentDescription = "Default Image",
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(4.dp))
    )
}

private fun getDummyName(): String {
    val names = listOf(
        "Fluffy",
        "Whiskers",
        "Mittens",
        "Socks",
        "Tiger",
        "Leo",
        "Simba",
        "Garfield",
        "Salem",
        "Felix"
    )
    return names.random()
}

private fun getDummyDescription(): String {
    val descriptions = listOf(
        "A cute little kitty with a fluffy tail.",
        "A playful kitten with big blue eyes.",
        "A majestic cat with a regal bearing.",
        "A mischievous feline with a devilish grin.",
        "A sleepy cat curled up in a ball.",
        "A curious kitten exploring its surroundings.",
        "A friendly cat with a gentle disposition.",
        "A lazy cat that loves to eat lasagna.",
        "A mysterious cat with an enigmatic gaze.",
        "A loyal cat that loves to cuddle."
    )
    return descriptions.random()
}

