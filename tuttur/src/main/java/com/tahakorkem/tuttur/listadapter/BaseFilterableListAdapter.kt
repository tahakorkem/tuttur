package com.tahakorkem.tuttur.listadapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.tahakorkem.tuttur.listadapter.common.Identifiable
import com.tahakorkem.tuttur.listadapter.common.TutturDiffCallback

abstract class BaseFilterableListAdapter<I : Identifiable, VDB : ViewDataBinding>
@JvmOverloads constructor(
    @LayoutRes layoutId: Int,
    diffCallback: DiffUtil.ItemCallback<I> = TutturDiffCallback()
) :
    BaseListAdapter<I, VDB>(layoutId, diffCallback) {

    /**
     * Filters the list with the given [constraint] and updates the adapter with the filtered items.
     * */
    fun filter(constraint: String?) {
        if (!::originalList.isInitialized) {
            error("It must be called submitList() at least one before filtering!")
        }
        super.submitList(originalList.onFilter(constraint))
    }

    protected abstract fun List<I>.onFilter(constraint: String?): List<I>

    final override fun submitList(list: List<I>?, commitCallback: Runnable?) {
        originalList = list?.toList() ?: emptyList()
        super.submitList(list, commitCallback)
    }

    final override fun submitList(list: List<I>?) {
        originalList = list?.toList() ?: emptyList()
        super.submitList(list)
    }

    private lateinit var originalList: List<I>

}