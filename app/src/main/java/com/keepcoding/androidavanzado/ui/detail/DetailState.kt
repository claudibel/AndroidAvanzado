package com.keepcoding.androidavanzado.ui.detail

import com.keepcoding.androidavanzado.domain.SuperHero

sealed class DetailState {
    data class Success(val hero: SuperHero) : DetailState()
    data class Failure(val error: String?) : DetailState()
    data class NetworkFailure(val code: Int) : DetailState()
}