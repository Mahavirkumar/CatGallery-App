package com.kmdev.catgallery.api

import com.kmdev.catgallery.data.models.CatImageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {

    @GET("images/search")
    suspend fun getCatImages(@Query("limit") limit: Int): Response<List<CatImageModel>>
}