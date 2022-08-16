package com.tahakorkem.tuttur.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tahakorkem.tuttur.listadapter.common.BaseViewHolder
import com.tahakorkem.tuttur.listadapter.common.TutturDiffCallback
import com.tahakorkem.tuttur.listadapter.common.Identifiable

abstract class BaseListAdapter<I : Identifiable, VDB : ViewDataBinding>
@JvmOverloads constructor(
    @LayoutRes private val layoutId: Int,
    diffCallback: DiffUtil.ItemCallback<I> = TutturDiffCallback()
) :
    ListAdapter<I, BaseViewHolder<VDB>>(diffCallback) {

    final override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<VDB> {
        return BaseViewHolder.create(
            layoutId,
            LayoutInflater.from(parent.context),
            parent,
            onCreate = { onCreate(viewType) }
        )
    }

    final override fun onBindViewHolder(holder: BaseViewHolder<VDB>, position: Int) {
        val item = getItem(position)
        holder.bindTo { onBind(holder, position, item) }
    }

    final override fun onBindViewHolder(
        holder: BaseViewHolder<VDB>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    open fun VDB.onBind(holder: BaseViewHolder<VDB>, index: Int, item: I) = Unit

    open fun VDB.onCreate(viewType: Int) = Unit

}