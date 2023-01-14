package com.keepcoding.androidavanzado.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "superheros")
@Parcelize
data class SuperHeroLocal(
    @PrimaryKey val id : String,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "photo") val photo : String,
    @ColumnInfo(name = "description") val description : String,
    @ColumnInfo(name = "favorite") val favorite : Boolean
    ): Parcelable
