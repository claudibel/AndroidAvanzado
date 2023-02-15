package com.keepcoding.androidavanzado.fakes

import com.keepcoding.androidavanzado.data.local.LocalDataSource
import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal
import com.keepcoding.androidavanzado.utils.generateFavoriteHerosLocal
import com.keepcoding.androidavanzado.utils.generateHerosLocal

class FakeLocalDataSource: LocalDataSource {

    private var firstCall = true
    private var favoriteList = generateFavoriteHerosLocal()

    override fun getHeros(): List<SuperHeroLocal> {
        return if(firstCall){
            firstCall = false
            emptyList()
        }else{
            generateHerosLocal()
        }
    }

    override fun insertHeros(remoteSuperHeros: List<SuperHeroLocal>) {

    }

    override fun getFavoriteSuperheros(isFavorite: Boolean): List<SuperHeroLocal> {
        return favoriteList.ifEmpty {
            emptyList()
        }
    }

    override fun insertAllFavorites(superHeroLocal: List<SuperHeroLocal>) {
        TODO("Not yet implemented")
    }
}