package com.tahakorkem.tuttur

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.tahakorkem.tuttur.databinding.DialogPostDetailBinding
import com.tahakorkem.tuttur.dialog.BaseDialogFragment

class PostDetailDialog : BaseDialogFragment<DialogPostDetailBinding>(R.layout.dialog_post_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ThemeDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Post>("post")?.let { post ->
            binding.post = post
            binding.textViewContent.isVisible = post.content != null
            val glide = Glide.with(requireContext())
            glide.load(post.author.avatarUrl).circleCrop().into(binding.imageViewAuthor)
            if (post is Post.Image) {
                glide.load(post.imageUrl).into(binding.imageView)
            } else {
                binding.imageView.isVisible = false
            }
        }
    }

    companion object {
        const val TAG = "PostDetailDialog"
    }

}
