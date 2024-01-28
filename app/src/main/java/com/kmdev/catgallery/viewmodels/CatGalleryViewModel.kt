package com.kmdev.catgallery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmdev.catgallery.data.models.CatImageModel
import com.kmdev.catgallery.repository.CatGalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatGalleryViewModel @Inject constructor(private val catGalleryRepository: CatGalleryRepository) :
    ViewModel() {

    val catImagesList: StateFlow<List<CatImageModel>>
        get() = catGalleryRepository.catImagesList

    init {
        fetchCatImages()
    }

    fun fetchCatImages() {
        viewModelScope.launch {
            try {
                catGalleryRepository.getCatImages()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}