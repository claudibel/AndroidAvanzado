package com.keepcoding.androidavanzado.data.remote.response

import com.squareup.moshi.Json

data class SuperHeroLocation(
    @Json(name = "longitud") val longitud : String,
    @Json(name = "dateShow") val dateShow : String,
    @Json(name = "id") val id : String,
    @Json(name = "latitud") val latitud : String
)
