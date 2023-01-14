package com.keepcoding.androidavanzado.ui.superherolist

import com.keepcoding.androidavanzado.domain.SuperHero

sealed class SuperHeroListState{
    data class Success(val heros: List<SuperHero>): SuperHeroListState()
    data class Failure(val error: String?): SuperHeroListState()
    data class NetworkFailure(val code: Int): SuperHeroListState()
}
