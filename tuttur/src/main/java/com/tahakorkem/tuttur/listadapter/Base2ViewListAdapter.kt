package com.tahakorkem.tuttur.listadapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.tahakorkem.tuttur.listadapter.common.BaseViewHolder
import com.tahakorkem.tuttur.listadapter.common.Bindable
import com.tahakorkem.tuttur.listadapter.common.Identifiable
import com.tahakorkem.tuttur.listadapter.common.TutturDiffCallback

@Suppress("UNCHECKED_CAST")
abstract class Base2ViewListAdapter<I : Identifiable, I1 : I, VDB1 : ViewDataBinding, I2 : I, VDB2 : ViewDataBinding>
@JvmOverloads constructor(
    bindable1: Bindable<I1>,
    bindable2: Bindable<I2>,
    diffCallback: DiffUtil.ItemCallback<I> = TutturDiffCallback()
) : BaseMultiViewListAdapter<I>(diffCallback) {

    final override val bindables = listOf(bindable1, bindable2)

    final override fun ViewDataBinding.onBind(
        holder: BaseViewHolder<ViewDataBinding>,
        index: Int,
        item: I
    ) {
        when (holder.itemViewType) {
            0 -> {
                this as VDB1
                onBind1(holder as BaseViewHolder<VDB1>, index, item as I1)
            }
            1 -> {
                this as VDB2
                onBind2(holder as BaseViewHolder<VDB2>, index, item as I2)
            }
        }
    }

    final override fun ViewDataBinding.onCreate(viewType: Int) {
        when (viewType) {
            0 -> {
                this as VDB1
                onCreate1(viewType)
            }
            1 -> {
                this as VDB2
                onCreate2(viewType)
            }
        }
    }

    open fun VDB1.onBind1(holder: BaseViewHolder<VDB1>, index: Int, item: I1) = Unit

    open fun VDB2.onBind2(holder: BaseViewHolder<VDB2>, index: Int, item: I2) = Unit

    open fun VDB1.onCreate1(viewType: Int) = Unit

    open fun VDB2.onCreate2(viewType: Int) = Unit

}