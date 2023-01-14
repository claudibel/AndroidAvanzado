package com.keepcoding.androidavanzado.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHero(
    val id: String,
    val name: String,
    val description: String,
    val photo: String
) : Parcelable
