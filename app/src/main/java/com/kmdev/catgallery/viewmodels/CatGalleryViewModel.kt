package com.kmdev.catgallery.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmdev.catgallery.data.models.CatImageModel
import com.kmdev.catgallery.repository.CatGalleryRepository
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatGalleryViewModel @Inject constructor(private val catGalleryRepository: CatGalleryRepository,private val application: Application) :
    AndroidViewModel(application) {

    var networkStatus = false
    var backOnline = false
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

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(application, "No Internet Connection.", Toast.LENGTH_SHORT).show()
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(application, "We're back online.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}