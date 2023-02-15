package com.keepcoding.androidavanzado.utils

import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroLocation
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroRemote
import com.keepcoding.androidavanzado.domain.SuperHero
import com.squareup.moshi.Json

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

fun generateFavoriteHerosLocal(): List<SuperHeroLocal>{
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

fun generateToken(): String{
    return "remotelyGeneratedTokenFromUsernameAndPasswordAuthentication1985"
}

fun generateLocation(): List<SuperHeroLocation>{
    return(0 until 3).map{
        SuperHeroLocation(
            "${it.toDouble()}",
            "DateShow: $it",
            "ID: $it",
            "${it.toDouble()+0.255}"
        )
    }
}

fun generateIsFavorite(): Boolean{
    var binary = (0..1).random()
    return binary != 0
}

fun generateFavoriteList(): List<SuperHero>{
    return(0 until 10).map{
        SuperHero(
            "ID: $it",
            "Name: $it",
            "Photo: $it",
            "Description: $it"
        )
    }
}

