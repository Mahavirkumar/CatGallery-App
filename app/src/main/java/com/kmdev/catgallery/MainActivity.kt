package com.kmdev.catgallery

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kmdev.catgallery.screen.CatGalleryScreen
import com.kmdev.catgallery.utils.NetworkListener
import com.kmdev.catgallery.viewmodels.CatGalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var catGalleryViewModel: CatGalleryViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private lateinit var networkListener: NetworkListener

    @OptIn(ExperimentalCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catGalleryViewModel = ViewModelProvider(this).get(CatGalleryViewModel::class.java)
        lifecycleScope.launch {
            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(this@MainActivity)
                .collect { status ->
                    Log.d("NetworkListener", status.toString())
                    catGalleryViewModel.networkStatus = status
                    catGalleryViewModel.showNetworkStatus()
                }
        }

        setContent {
            CatGalleryScreen()
        }
    }
}
