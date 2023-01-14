package com.keepcoding.androidavanzado.utils

import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroRemote
import com.keepcoding.androidavanzado.domain.SuperHero

fun generateHerosRemote(): List<SuperHeroRemote>{
    return(0 until 10).map{
        SuperHeroRemote(
            "ID: $it",
            "Name: $it",
            "Photo: $it",
            "Description: $it",
            favorite = false
        )
    }
}

fun generateHerosLocal(): List<SuperHeroLocal>{
    return(0 until 10).map{
        SuperHeroLocal(
            "ID: $it",
            "Name: $it",
            "Photo: $it",
            "Description: $it",
            favorite = false
        )
    }
}

fun generateHeros(): List<SuperHero>{
    return(0 until 10).map{
        SuperHero(
            "ID: $it",
            "Name: $it",
            "Photo: $it",
            "Description: $it"
        )
    }
}