package com.keepcoding.androidavanzado.ui.favaritesuperheroviewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.data.RepositoryImpl
import com.keepcoding.androidavanzado.ui.detail.DetailViewModel
import com.keepcoding.androidavanzado.ui.favorite.FavoriteSuperHeroViewModel
import com.keepcoding.androidavanzado.utils.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteSuperHeroViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //SUT o UUT
    private lateinit var favoriteSuperHeroViewModel: FavoriteSuperHeroViewModel

    //Dependencies
    private lateinit var repositoryImpl: RepositoryImpl
    private lateinit var repository: Repository

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup(){
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `WHEN getFavoriteList THEN SUCCESS returns list of favorite superheros`() = runTest {
        //GIVEN
        repository = mockk()
        favoriteSuperHeroViewModel = FavoriteSuperHeroViewModel(repository)
        coEvery { repository.getFavoritesList() } returns generateFavoriteList()

        //WHEN
        val actual = favoriteSuperHeroViewModel.getFavoriteList()
        val actualLiveData = favoriteSuperHeroViewModel.favorites.getOrAwaitValue()

        //THEN
        Truth.assertThat(actualLiveData).isNotEmpty()
        Truth.assertThat(actualLiveData ).containsNoDuplicates()
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}