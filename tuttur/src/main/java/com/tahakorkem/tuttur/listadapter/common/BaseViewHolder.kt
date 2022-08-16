package com.tahakorkem.tuttur.listadapter.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<VDB : ViewDataBinding>(val binding: VDB) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {

        @Suppress("UNCHECKED_CAST")
        inline fun <VDB : ViewDataBinding> create(
            @LayoutRes layoutId: Int,
            inflater: LayoutInflater,
            parent: ViewGroup,
            onCreate: VDB.() -> Unit,
        ): BaseViewHolder<VDB> {
            val binding = DataBindingUtil.inflate<VDB>(inflater, layoutId, parent, false)
            binding.onCreate()
            return BaseViewHolder(binding)
        }

    }

    open fun bindTo(
        block: VDB.() -> Unit,
    ) {
        binding.apply {
            block()
            executePendingBindings()
        }
    }

}