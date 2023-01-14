package com.keepcoding.androidavanzado.data.mappers

import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroRemote
import com.keepcoding.androidavanzado.domain.SuperHero
import javax.inject.Inject

class RemoteToPresentationMapper @Inject constructor() {

    fun map(superHeroList: List<SuperHeroRemote>): List<SuperHero>{
        return superHeroList.map{ SuperHero(it.id, it.name, it.description, it.photo) }
    }

    fun map(superHero: SuperHeroRemote) : SuperHero {
        return SuperHero(superHero.id, superHero.name, superHero.description, superHero.photo)
    }
}