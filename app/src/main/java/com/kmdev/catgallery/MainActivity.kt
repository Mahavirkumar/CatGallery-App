package com.kmdev.catgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.kmdev.catgallery.models.CatImageModel

class MainActivity : ComponentActivity() {

    private val catViewModel: CatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val catImages by catViewModel.catImagesModel.collectAsState(initial = emptyList())
            CatList(catImages)

        }
    }
}

@Composable
fun CatList(catImageModels: List<CatImageModel>) {


    LazyColumn(content = {
        items(catImageModels) {
            CatListItem(imageUrl = it.url, imgWidth = it.width, imgHeight = it.height)
        }
    })
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CatListItem(imageUrl: String, imgWidth: Int?, imgHeight: Int) {
    // Customize the list item UI as needed
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
//        if (painter.state is AsyncImagePainter.State.Loading) {
//            CommonProgressSpinner()
//        }
    }
}

//@Composable
//fun CommonProgressSpinner() {
//    Row(
//        modifier = Modifier
//            .alpha(0.5f)
//            .background(Color.LightGray)
//            .fillMaxSize(),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        CircularProgressIndicator()
//    }
//}