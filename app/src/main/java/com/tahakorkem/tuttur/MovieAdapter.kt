package com.tahakorkem.tuttur

import com.tahakorkem.tuttur.databinding.ListItemMovieBinding
import com.tahakorkem.tuttur.listadapter.BaseListAdapter
import com.tahakorkem.tuttur.listadapter.common.BaseViewHolder

class MovieAdapter(
    private val callback: MovieCallback
) : BaseListAdapter<Movie, ListItemMovieBinding>(R.layout.list_item_movie) {

    override fun ListItemMovieBinding.onBind(
        holder: BaseViewHolder<ListItemMovieBinding>,
        index: Int,
        item: Movie
    ) {
        movie = item
        callback = this@MovieAdapter.callback
    }

}