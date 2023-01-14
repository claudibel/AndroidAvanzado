package com.keepcoding.androidavanzado.ui.superherolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.data.RepositoryImpl
import com.keepcoding.androidavanzado.utils.generateHeros
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
class SuperHeroListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //SUT o UUT
    private lateinit var supeHeroListViewModel: SuperHeroListViewModel

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
    fun `WHEN getHeros THEN SUCCESS returns list of superheros`() = runTest {
        //GIVEN
        repository = mockk()
        supeHeroListViewModel = SuperHeroListViewModel(repository)
        coEvery { repository.getHerosWithCache() } returns generateHeros()

        //WHEN
        val actual = supeHeroListViewModel.getHeros()
        val actualLiveData = supeHeroListViewModel.heros.getOrAwaitValue()

        //THEN
        Truth.assertThat(actualLiveData).containsExactlyElementsIn(generateHeros())
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}