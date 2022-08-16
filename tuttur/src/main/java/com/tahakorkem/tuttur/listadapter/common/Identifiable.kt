package com.tahakorkem.tuttur.listadapter.common

interface Identifiable {
    val id: Any
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}