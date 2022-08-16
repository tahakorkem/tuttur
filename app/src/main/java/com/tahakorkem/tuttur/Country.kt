package com.tahakorkem.tuttur

import com.tahakorkem.tuttur.listadapter.common.Identifiable

data class Country(
    override val id: Int,
    val name: String,
    val emoji: String,
) : Identifiable
