package com.keepcoding.androidavanzado.data.remote

import com.google.common.truth.Truth
import com.keepcoding.androidavanzado.base.BaseNetworkTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

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
}