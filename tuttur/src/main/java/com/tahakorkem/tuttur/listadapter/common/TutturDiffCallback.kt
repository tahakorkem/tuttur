package com.tahakorkem.tuttur.listadapter.common

import androidx.recyclerview.widget.DiffUtil

internal class TutturDiffCallback<I : Identifiable> : DiffUtil.ItemCallback<I>() {
    override fun areItemsTheSame(oldItem: I, newItem: I): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: I, newItem: I): Boolean =
        oldItem == newItem
}