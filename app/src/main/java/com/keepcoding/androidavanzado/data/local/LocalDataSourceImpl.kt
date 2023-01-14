package com.keepcoding.androidavanzado.data.local

import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: SuperHeroDAO, private val favoriteDao: FavoriteSuperHeroDAO): LocalDataSource {

    override fun getHeros(): List<SuperHeroLocal> {
        return dao.getAllSuperheros()
    }

    override fun insertHeros(remoteSuperHeros: List<SuperHeroLocal>) {
        return dao.insertAll(remoteSuperHeros)
    }

    override fun getFavoriteSuperheros(isFavorite: Boolean): List<SuperHeroLocal> {
        return dao.getAllFavoriteSuperheros(isFavorite)
    }

    override fun insertAllFavorites(superHeroLocal: List<SuperHeroLocal>) {
        return favoriteDao.insertAllFavorites(superHeroLocal)
    }

}