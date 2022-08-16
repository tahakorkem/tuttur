package com.tahakorkem.tuttur.listadapter.common

import androidx.annotation.LayoutRes
import kotlin.reflect.KClass

data class Bindable<T : Identifiable>(
    val clazz: KClass<T>,
    @LayoutRes val layoutId: Int
)

inline fun <reified T : Identifiable> bind(@LayoutRes resId: Int): Bindable<T> {
    return Bindable(T::class, resId)
}