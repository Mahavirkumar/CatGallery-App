package com.kmdev.catgallery

import com.kmdev.catgallery.repository.CatGalleryRepository
import com.kmdev.catgallery.viewmodels.CatGalleryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var repository: CatGalleryRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_GetProducts() = runTest{
        Mockito.`when`(repository.getCatImages()).thenReturn(Unit)

        val sut = CatGalleryViewModel(repository)
        sut.fetchCatImages()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.catImagesList.value
        Assert.assertEquals(0, result.size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}