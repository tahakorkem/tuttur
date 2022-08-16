package com.tahakorkem.tuttur

data class MovieCallback(
    val onClick: (Movie) -> Unit
)