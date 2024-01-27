package com.kmdev.catgallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmdev.catgallery.api.CatApi
import com.kmdev.catgallery.models.CatImageModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {

    private val catApi = CatApi()
    val catImagesModel: StateFlow<List<CatImageModel>> get() = catApi.catImagesModel

    init {
        fetchCatImages()
    }

    private fun fetchCatImages() {
        viewModelScope.launch {
            try {
                catApi.getCatImages()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}