package com.keepcoding.androidavanzado.data

import com.google.common.truth.Truth
import com.keepcoding.androidavanzado.data.local.LocalDataSource
import com.keepcoding.androidavanzado.data.mappers.LocalToPresentationMapper
import com.keepcoding.androidavanzado.data.mappers.RemoteToLocalMapper
import com.keepcoding.androidavanzado.data.mappers.RemoteToPresentationMapper
import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.keepcoding.androidavanzado.fakes.FakeLocalDataSource
import com.keepcoding.androidavanzado.utils.generateFavoriteHerosLocal
import com.keepcoding.androidavanzado.utils.generateHeros
import com.keepcoding.androidavanzado.utils.generateHerosRemote
import com.keepcoding.androidavanzado.utils.generateToken
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    //UUT o SUT
    private lateinit var repositoryImpl: RepositoryImpl
    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup(){
        localDataSource = mockk()
        remoteDataSource = mockk()

        repositoryImpl = RepositoryImpl(
            localDataSource,
            remoteDataSource,
            RemoteToPresentationMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

    }

    @Test
    fun `WHEN login THEN SUCCESS return token from remote`() = runTest {
        //GIVEN
        coEvery { remoteDataSource.login() } returns generateToken()

        //WHEN
        val actual = repositoryImpl.login()

        //THEN
        Truth.assertThat(actual).isNotEmpty()
        coVerify(exactly = 1) { remoteDataSource.login() }
    }

    @Test
    fun `WHEN getHeros THEN SUCCESS return list from local and remote called`() = runTest {

        //GIVEN
        coEvery { localDataSource.getHeros() } returns emptyList()
        coEvery { localDataSource.insertHeros(any()) } returns Unit
        coEvery { remoteDataSource.getHeros() } returns generateHerosRemote()

        //WHEN
        val actual = repositoryImpl.getHerosWithCache()

        //THEN
        coVerify(exactly = 3) { localDataSource.getHeros() }
    }

    @Test
    fun `WHEN getHeros THEN SUCCESS return list from local and remote called with FAKE`() = runTest {

        //GIVEN
        val localDataSource = FakeLocalDataSource()
        repositoryImpl = RepositoryImpl(
            localDataSource,
            remoteDataSource,
            RemoteToPresentationMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )
        coEvery { remoteDataSource.getHeros() } returns generateHerosRemote()

        //WHEN
        val actual = repositoryImpl.getHerosWithCache()

        //THEN
        coVerify{ remoteDataSource.getHeros()}
        Truth.assertThat(actual).isNotEmpty()
        Truth.assertThat(actual.first().name).isEqualTo("Name: 0")
    }

    @Test
    fun `WHEN getFavoriteList THEN return list from local with FAKE`() = runTest {

        //GIVEN

        coEvery { localDataSource.getFavoriteSuperheros(isFavorite = true) } returns generateFavoriteHerosLocal()

        //WHEN
        val actual = repositoryImpl.getFavoritesList()

        //THEN
        coVerify{ localDataSource.getFavoriteSuperheros(isFavorite = true)}
        Truth.assertThat(actual).containsNoDuplicates()
    }
}