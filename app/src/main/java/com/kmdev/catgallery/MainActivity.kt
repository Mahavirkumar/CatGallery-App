package com.kmdev.catgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kmdev.catgallery.screen.CatGalleryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatGalleryScreen()

        }
    }
}

//@Composable
//fun CatList(catImageModels: List<CatImageModel>) {
//
//
//    LazyColumn(content = {
//        items(catImageModels) {
//            CatListItem(imageUrl = it.url, imgWidth = it.width, imgHeight = it.height)
//        }
//    })
//}
//
//@OptIn(ExperimentalCoilApi::class)
//@Composable
//fun CatListItem(imageUrl: String, imgWidth: Int?, imgHeight: Int) {
//    // Customize the list item UI as needed
//    val painter = rememberAsyncImagePainter(imageUrl)
//
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(imgHeight.dp)
//    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(imageUrl)
//                .crossfade(true)
//                .build(),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxSize()
//                .clip(RoundedCornerShape(4.dp))
//        )
////        if (painter.state is AsyncImagePainter.State.Loading) {
////            CommonProgressSpinner()
////        }
//    }
//}

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