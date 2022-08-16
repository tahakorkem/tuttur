package com.tahakorkem.tuttur

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tahakorkem.tuttur.databinding.ListItemMovieBinding

class MovieAdapterLegacy(
    private val callback: MovieCallback
) : ListAdapter<Movie, MovieAdapterLegacy.ViewHolder>(MovieDiffCallback()) {

    inner class ViewHolder(private val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(movie: Movie) {
            binding.movie = movie
            binding.callback = callback
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class MovieDiffCallback : ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}

