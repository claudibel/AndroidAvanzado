package com.keepcoding.androidavanzado.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal

@Dao
interface SuperHeroDAO {

    @Query("SELECT * FROM superheros")
    fun getAllSuperheros(): List<SuperHeroLocal>

    @Query("SELECT * FROM superheros WHERE favorite = :isFavorite")
    fun getAllFavoriteSuperheros(isFavorite: Boolean): List<SuperHeroLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(superHero: List<SuperHeroLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSuperhero(superHero: SuperHeroLocal)
}