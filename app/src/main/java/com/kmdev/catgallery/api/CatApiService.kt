package com.kmdev.catgallery.api

import com.kmdev.catgallery.models.CatImageModel
import retrofit2.Response
import retrofit2.http.GET

interface CatApiService {

    @GET("images/search?limit=10")
    suspend fun getCatImages(): Response<List<CatImageModel>>
}