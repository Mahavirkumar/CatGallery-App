package com.kmdev.catgallery.api

import com.kmdev.catgallery.api.ApiConstants.BASE_URL
import com.kmdev.catgallery.models.CatImageModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatApi {
    private val catApiService: CatApiService

    private val _catImagesModel = MutableStateFlow<List<CatImageModel>>(emptyList())
    val catImagesModel: StateFlow<List<CatImageModel>> get() = _catImagesModel

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        catApiService = retrofit.create(CatApiService::class.java)
    }

    suspend fun getCatImages() {
        val response = catApiService.getCatImages()
        if (response.isSuccessful && response.body() != null) {
            _catImagesModel.emit(response.body()!!)
        }
    }
}