package com.keepcoding.androidavanzado.data.remote

import com.google.common.truth.Truth
import com.keepcoding.androidavanzado.base.BaseNetworkTest
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceImplTest: BaseNetworkTest() {

    //SUT o UUT
    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Test
    fun `WHEN getHeros THEN SUCCESS and returns hero list`() = runTest {
        //GIVEN
        remoteDataSourceImpl = RemoteDataSourceImpl(api)

        //WHEN
        val actual = remoteDataSourceImpl.getHeros()

        //THEN
        Truth.assertThat(actual).hasSize(24)
        Truth.assertThat(actual[0].name).isEqualTo("Maestro Roshi")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN getHeroLocation THEN SUCCESS and returns hero list of locations`() = runTest {
        //GIVEN
        remoteDataSourceImpl = RemoteDataSourceImpl(api)

        //WHEN
        val actual = remoteDataSourceImpl.getHeroLocations("1985A353-157F-4C0B-A789-FD5B4F8DABDB")

        //THEN
        //Truth.assertThat(actual).hasSize(5)
        //Truth.assertThat(actual).containsNoDuplicates()
        Truth.assertThat(actual).isNotEmpty()
    }
}