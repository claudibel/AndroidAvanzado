package com.keepcoding.androidavanzado.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.data.RepositoryImpl
import com.keepcoding.androidavanzado.ui.detail.DetailViewModel
import com.keepcoding.androidavanzado.utils.generateIsFavorite
import com.keepcoding.androidavanzado.utils.generateLocation
import com.keepcoding.androidavanzado.utils.getOrAwaitValue
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
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //SUT o UUT
    private lateinit var detailViewModel: DetailViewModel

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
    fun `WHEN getLocatation THEN SUCCESS returns list of heroes locations`() = runTest {
        //GIVEN
        repository = mockk()
        detailViewModel = DetailViewModel(repository)
        coEvery { repository.getHeroLocations("1985A353-157F-4C0B-A789-FD5B4F8DABDB") } returns generateLocation()

        //WHEN
        val actual = detailViewModel.getHeroLocations("1985A353-157F-4C0B-A789-FD5B4F8DABDB")
        val actualLiveData = detailViewModel.location.getOrAwaitValue()

        //THEN
        Truth.assertThat(actualLiveData).isNotEmpty()
        Truth.assertThat(actualLiveData ).containsNoDuplicates()
    }

    @Test
    fun `WHEN isFavorite THEN SUCCESS returns true or false`() = runTest {
        //GIVEN
        repository = mockk()
        detailViewModel = DetailViewModel(repository)
        coEvery { repository.isFavorite("1985A353-157F-4C0B-A789-FD5B4F8DABDB") } returns generateIsFavorite()

        //WHEN
        val actual = detailViewModel.isFavorite("1985A353-157F-4C0B-A789-FD5B4F8DABDB")
        val actualLiveData = detailViewModel.isFavoriteHero.getOrAwaitValue()

        //THEN
        Truth.assertThat(actualLiveData).isAnyOf(true, false)
        Truth.assertThat(actualLiveData).isNotNull()
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}