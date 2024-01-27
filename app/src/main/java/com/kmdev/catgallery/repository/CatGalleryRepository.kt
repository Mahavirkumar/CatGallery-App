package com.kmdev.catgallery.repository

import com.kmdev.catgallery.api.CatApiService
import com.kmdev.catgallery.data.models.CatImageModel
import com.kmdev.catgallery.utils.ApiConstants.requestLimit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CatGalleryRepository @Inject constructor(private val catApiService: CatApiService) {

    private val _catImagesModelList = MutableStateFlow<List<CatImageModel>>(emptyList())
    val catImagesList: StateFlow<List<CatImageModel>>
        get() = _catImagesModelList

    suspend fun getCatImages() {
        val response = catApiService.getCatImages(requestLimit)
        if (response.isSuccessful && response.body() != null) {
            _catImagesModelList.emit(response.body()!!)
        }
    }
}