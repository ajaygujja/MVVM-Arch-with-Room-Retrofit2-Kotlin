package com.example.kotlinroomretrofit.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val login: String,
    val type: String,
    val avatar_url: String
) : Parcelable