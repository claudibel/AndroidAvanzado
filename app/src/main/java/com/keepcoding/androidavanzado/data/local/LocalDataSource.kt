package com.keepcoding.androidavanzado.data.local

import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal

interface LocalDataSource {
    fun getHeros(): List<SuperHeroLocal>
    fun insertHeros(remoteSuperHeros: List<SuperHeroLocal>)
    fun getFavoriteSuperheros(isFavorite: Boolean): List<SuperHeroLocal>
    fun insertAllFavorites(superHeroLocal: List<SuperHeroLocal>)
}