package com.tahakorkem.tuttur.listadapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.tahakorkem.tuttur.listadapter.common.BaseViewHolder
import com.tahakorkem.tuttur.listadapter.common.Bindable
import com.tahakorkem.tuttur.listadapter.common.Identifiable
import com.tahakorkem.tuttur.listadapter.common.TutturDiffCallback

@Suppress("UNCHECKED_CAST")
abstract class Base5ViewListAdapter<I : Identifiable, I1 : I, VDB1 : ViewDataBinding, I2 : I, VDB2 : ViewDataBinding, I3 : I, VDB3 : ViewDataBinding, I4 : I, VDB4 : ViewDataBinding, I5 : I, VDB5 : ViewDataBinding>
@JvmOverloads constructor(
    bindable1: Bindable<I1>,
    bindable2: Bindable<I2>,
    bindable3: Bindable<I3>,
    bindable4: Bindable<I4>,
    bindable5: Bindable<I5>,
    diffCallback: DiffUtil.ItemCallback<I> = TutturDiffCallback()
) : BaseMultiViewListAdapter<I>(diffCallback) {

    final override val bindables = listOf(bindable1, bindable2, bindable3, bindable4, bindable5)

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
            2 -> {
                this as VDB3
                onBind3(holder as BaseViewHolder<VDB3>, index, item as I3)
            }
            3 -> {
                this as VDB4
                onBind4(holder as BaseViewHolder<VDB4>, index, item as I4)
            }
            4 -> {
                this as VDB5
                onBind5(holder as BaseViewHolder<VDB5>, index, item as I5)
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
            2 -> {
                this as VDB3
                onCreate3(viewType)
            }
            3 -> {
                this as VDB4
                onCreate4(viewType)
            }
            4 -> {
                this as VDB5
                onCreate5(viewType)
            }
        }
    }

    open fun VDB1.onBind1(holder: BaseViewHolder<VDB1>, index: Int, item: I1) = Unit

    open fun VDB2.onBind2(holder: BaseViewHolder<VDB2>, index: Int, item: I2) = Unit

    open fun VDB3.onBind3(holder: BaseViewHolder<VDB3>, index: Int, item: I3) = Unit

    open fun VDB4.onBind4(holder: BaseViewHolder<VDB4>, index: Int, item: I4) = Unit

    open fun VDB5.onBind5(holder: BaseViewHolder<VDB5>, index: Int, item: I5) = Unit

    open fun VDB1.onCreate1(viewType: Int) = Unit

    open fun VDB2.onCreate2(viewType: Int) = Unit

    open fun VDB3.onCreate3(viewType: Int) = Unit

    open fun VDB4.onCreate4(viewType: Int) = Unit

    open fun VDB5.onCreate5(viewType: Int) = Unit

}