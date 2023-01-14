package com.keepcoding.androidavanzado.data

import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroLocation
import com.keepcoding.androidavanzado.domain.SuperHero
import com.keepcoding.androidavanzado.ui.detail.DetailState
import com.keepcoding.androidavanzado.ui.superherolist.SuperHeroListState
import retrofit2.Call

interface Repository {

    suspend fun getHeros(): List<SuperHero>
    suspend fun getHeroLocations(heroId: String): List<SuperHeroLocation>
    suspend fun getHeroDetail(name: String): DetailState
    suspend fun getHerosWithCache(): List<SuperHero>
    suspend fun getHerosWithException(): SuperHeroListState
    suspend fun login(): String
    suspend fun favorite(heroId: String)
    suspend fun getFavoritesList(): List<SuperHero>
    suspend fun isFavorite(heroId: String) : Boolean
}