package com.tahakorkem.tuttur

import android.content.Context
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.tahakorkem.tuttur.databinding.ListItemFooterBinding
import com.tahakorkem.tuttur.databinding.ListItemImagePostBinding
import com.tahakorkem.tuttur.databinding.ListItemSectionHeaderBinding
import com.tahakorkem.tuttur.databinding.ListItemTextPostBinding
import com.tahakorkem.tuttur.listadapter.Base4ViewListAdapter
import com.tahakorkem.tuttur.listadapter.common.BaseViewHolder
import com.tahakorkem.tuttur.listadapter.common.bind

class PostAdapter(
    context: Context,
    private val onClick: (Post) -> Unit,
) : Base4ViewListAdapter<PostItem, Post.Text, ListItemTextPostBinding, Post.Image, ListItemImagePostBinding, SectionHeader, ListItemSectionHeaderBinding, Footer, ListItemFooterBinding>(
    bind(R.layout.list_item_text_post),
    bind(R.layout.list_item_image_post),
    bind(R.layout.list_item_section_header),
    bind(R.layout.list_item_footer)
) {

    private val glide = Glide.with(context)

    override fun ListItemTextPostBinding.onBind1(
        holder: BaseViewHolder<ListItemTextPostBinding>,
        index: Int,
        item: Post.Text
    ) {
        textPost = item
        glide.load(item.author.avatarUrl).circleCrop().into(holder.binding.imageViewAuthor)
        rootLayout.setOnClickListener {
            onClick(item)
        }
    }

    override fun ListItemImagePostBinding.onBind2(
        holder: BaseViewHolder<ListItemImagePostBinding>,
        index: Int,
        item: Post.Image
    ) {
        imagePost = item
        textViewContent.isVisible = item.content != null
        glide.load(item.author.avatarUrl).circleCrop().into(holder.binding.imageViewAuthor)
        glide.load(item.imageUrl).into(holder.binding.imageView)
        rootLayout.setOnClickListener {
            onClick(item)
        }
    }

    override fun ListItemSectionHeaderBinding.onBind3(
        holder: BaseViewHolder<ListItemSectionHeaderBinding>,
        index: Int,
        item: SectionHeader
    ) {
        sectionHeader = item
    }

}