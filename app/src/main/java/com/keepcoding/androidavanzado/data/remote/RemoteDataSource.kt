package com.keepcoding.androidavanzado.data.remote

import com.keepcoding.androidavanzado.data.remote.response.SuperHeroLocation
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroRemote
import retrofit2.Call

interface RemoteDataSource {
    suspend fun getHerosWithException() : Result<List<SuperHeroRemote>>
    suspend fun getHeros() : List<SuperHeroRemote>
    suspend fun login() : String
    suspend fun getHeroLocations(heroId : String) : List<SuperHeroLocation>
    suspend fun getHeroDetail(name : String) : Result<SuperHeroRemote?>
    suspend fun favorite(heroId: String)
}