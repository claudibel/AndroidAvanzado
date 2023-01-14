package com.keepcoding.androidavanzado.data.remote

import com.keepcoding.androidavanzado.data.remote.request.FavoriteRequest
import com.keepcoding.androidavanzado.data.remote.request.HerosRequest
import com.keepcoding.androidavanzado.data.remote.request.LocationRequest
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroLocation
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroRemote
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DragonBallAPI {

    @POST("/api/auth/login")
    suspend fun login() : String

    @POST("/api/data/herolike")
    suspend fun favorite(@Body favoriteRequest: FavoriteRequest)

    @POST("/api/heros/all")
    suspend fun getHeros(@Body herosRequest : HerosRequest) : List<SuperHeroRemote>

    @POST("/api/heros/locations")
    suspend fun getHeroLocations(@Body locationRequest: LocationRequest) : List<SuperHeroLocation>

    @POST("/api/heros/all")
    suspend fun getHeroDetail(@Body herosRequest : HerosRequest) : List<SuperHeroRemote>

    @POST("/api/heros/all")
    suspend fun getHerosWithException(@Body herosRequest : HerosRequest) : List<SuperHeroRemote>
}