package com.tahakorkem.tuttur

import android.os.Parcelable
import com.tahakorkem.tuttur.listadapter.common.Identifiable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    override val id: Int,
    val title: String,
    val description: String,
    val rating: Float,
) : Identifiable, Parcelable