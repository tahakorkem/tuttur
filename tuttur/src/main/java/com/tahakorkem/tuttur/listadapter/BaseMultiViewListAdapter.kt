package com.tahakorkem.tuttur.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tahakorkem.tuttur.listadapter.common.BaseViewHolder
import com.tahakorkem.tuttur.listadapter.common.Bindable
import com.tahakorkem.tuttur.listadapter.common.TutturDiffCallback
import com.tahakorkem.tuttur.listadapter.common.Identifiable

abstract class BaseMultiViewListAdapter<I : Identifiable>
@JvmOverloads constructor(
    diffCallback: DiffUtil.ItemCallback<I> = TutturDiffCallback()
) :
    ListAdapter<I, BaseViewHolder<ViewDataBinding>>(diffCallback) {

    protected abstract val bindables: List<Bindable<out I>>
    private val bindableIndicesMap by lazy {
        bindables.toDistinctMapIndexed { i, item -> item.clazz to i }
    }

    final override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ViewDataBinding> {
        return BaseViewHolder.create(
            bindables[viewType].layoutId,
            LayoutInflater.from(parent.context),
            parent,
            onCreate = {
                onCreate(viewType)
            }
        )
    }

    final override fun onBindViewHolder(
        holder: BaseViewHolder<ViewDataBinding>,
        position: Int
    ) {
        val item = getItem(position)
        holder.bindTo {
            onBind(holder, position, item)
        }
    }

    final override fun onBindViewHolder(
        holder: BaseViewHolder<ViewDataBinding>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    final override fun getItemViewType(position: Int): Int {
        return getItem(position)::class.let { clazz ->
            bindableIndicesMap[clazz] ?: error("No proper bindable found for $clazz")
        }
    }

    open fun ViewDataBinding.onBind(holder: BaseViewHolder<ViewDataBinding>, index: Int, item: I) =
        Unit

    open fun ViewDataBinding.onCreate(viewType: Int) = Unit

}

private inline fun <T, K, V> Iterable<T>.toDistinctMapIndexed(block: (index: Int, item: T) -> Pair<K, V>): Map<K, V> {
    val map = mutableMapOf<K, V>()
    for ((i, item) in this.withIndex()) {
        val (key, value) = block(i, item)
        if (key in map) {
            error("Key $key is already in the map. Duplicated keys are not allowed.")
        }
        map[key] = value
    }
    return map
}