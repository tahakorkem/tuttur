package com.tahakorkem.tuttur

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tahakorkem.tuttur.databinding.*

class PostAdapterLegacy(
    private val onClick: (Post) -> Unit,
) : ListAdapter<PostItem, PostAdapterLegacy.ViewHolder>(PostDiffCallback()) {

    inner class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: PostItem) {
            when(item) {
                is Post.Text -> {
                    binding as ListItemTextPostBinding
                    binding.textPost = item
                    binding.root.setOnClickListener { onClick(item) }
                    binding.executePendingBindings()
                }
                is Post.Image -> {
                    binding as ListItemImagePostBinding
                    binding.imagePost = item
                    binding.textViewContent.isVisible = item.content != null
                    binding.root.setOnClickListener { onClick(item) }
                    binding.executePendingBindings()
                }
                is SectionHeader -> {
                    binding as ListItemSectionHeaderBinding
                    binding.sectionHeader = item
                    binding.executePendingBindings()
                }
                else -> Unit
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = when(viewType) {
            0 -> ListItemTextPostBinding.inflate(layoutInflater, parent, false)
            1 -> ListItemImagePostBinding.inflate(layoutInflater, parent, false)
            2 -> ListItemSectionHeaderBinding.inflate(layoutInflater, parent, false)
            3 -> ListItemFooterBinding.inflate(layoutInflater, parent, false)
            else -> throw IllegalArgumentException("Unknown view type")
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is Post.Text -> 0
            is Post.Image -> 1
            is SectionHeader -> 2
            is Footer -> 3
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<PostItem>() {
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem == newItem
        }

    }
}