package com.keepcoding.androidavanzado.data.remote

import com.keepcoding.androidavanzado.data.remote.request.FavoriteRequest
import com.keepcoding.androidavanzado.data.remote.request.HerosRequest
import com.keepcoding.androidavanzado.data.remote.request.LocationRequest
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroLocation
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroRemote
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: DragonBallAPI
): RemoteDataSource {
    override suspend fun getHerosWithException(): Result<List<SuperHeroRemote>> {
        return kotlin.runCatching { api.getHerosWithException(HerosRequest()) }
    }

    override suspend fun getHeros(): List<SuperHeroRemote> {
        return api.getHeros(HerosRequest())
    }

    override suspend fun login(): String {
        return api.login()
    }

    override suspend fun getHeroLocations(heroId: String): List<SuperHeroLocation> {
        return api.getHeroLocations(LocationRequest(heroId))
    }

    override suspend fun getHeroDetail(name: String): Result<SuperHeroRemote?> {
        return kotlin.runCatching { api.getHeroDetail(HerosRequest(name)).firstOrNull() }
    }

    override suspend fun favorite(heroId: String){
        //println("Valor retornado por favorito :" +api.favorite(FavoriteRequest(heroId)).toString())
        return api.favorite(FavoriteRequest(heroId))
    }
}