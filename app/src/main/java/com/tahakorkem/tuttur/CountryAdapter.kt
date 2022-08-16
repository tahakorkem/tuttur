package com.tahakorkem.tuttur

import com.tahakorkem.tuttur.databinding.ListItemCountryBinding
import com.tahakorkem.tuttur.listadapter.BaseFilterableListAdapter
import com.tahakorkem.tuttur.listadapter.common.BaseViewHolder

class CountryAdapter :
    BaseFilterableListAdapter<Country, ListItemCountryBinding>(R.layout.list_item_country) {

    override fun ListItemCountryBinding.onBind(
        holder: BaseViewHolder<ListItemCountryBinding>,
        index: Int,
        item: Country
    ) {
        country = item
    }

    override fun List<Country>.onFilter(constraint: String?): List<Country> {
        return filter { it.name.contains(constraint ?: "", ignoreCase = true) }
    }

}
