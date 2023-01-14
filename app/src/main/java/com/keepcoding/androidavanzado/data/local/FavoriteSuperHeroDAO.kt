package com.keepcoding.androidavanzado.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal

@Dao
interface FavoriteSuperHeroDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFavorites(superHero: List<SuperHeroLocal>)

}