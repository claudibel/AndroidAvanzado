package com.keepcoding.androidavanzado.data.local.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keepcoding.androidavanzado.data.local.FavoriteSuperHeroDAO
import com.keepcoding.androidavanzado.data.local.SuperHeroDAO


@Database(entities = [SuperHeroLocal::class, FavoriteSuperHeroLocal::class], version = 2)
abstract class SuperHeroDatabase : RoomDatabase(){
    abstract fun getDAO() : SuperHeroDAO
    abstract fun getFavoriteDAO() : FavoriteSuperHeroDAO
}