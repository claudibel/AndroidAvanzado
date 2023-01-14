package com.keepcoding.androidavanzado.data.mappers

import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroRemote
import com.keepcoding.androidavanzado.domain.SuperHero
import javax.inject.Inject

class RemoteToLocalMapper@Inject constructor() {

    fun map(superHeroRemoteList: List<SuperHeroRemote>): List<SuperHeroLocal>{
        return superHeroRemoteList.map{SuperHeroLocal(it.id, it.name, it.photo, it.description, it.favorite)}
    }

    fun map(superHeroRemote: SuperHeroRemote) : SuperHeroLocal {
        return SuperHeroLocal(superHeroRemote.id, superHeroRemote.name, superHeroRemote.photo, superHeroRemote.description, superHeroRemote.favorite)
    }
}