package com.tahakorkem.tuttur

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.tahakorkem.tuttur.databinding.FragmentPostListBinding
import com.tahakorkem.tuttur.fragment.BaseFragment

class PostListFragment : BaseFragment<FragmentPostListBinding>(R.layout.fragment_post_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PostAdapter(requireContext()) {
            val dialog = PostDetailDialog()
            dialog.arguments = bundleOf("post" to it)
            dialog.show(parentFragmentManager, PostDetailDialog.TAG)
        }

        binding.recyclerViewPosts.adapter = adapter

        val user1 = User("Taha Körkem", "https://picsum.photos/id/1074/200/200")
        val user2 = User("John Doe", "https://picsum.photos/id/237/200/200")

        adapter.submitList(
            listOf(
                SectionHeader(1, "Section 1"),
                Post.Text(2, user1, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean a justo ex. Pellentesque vestibulum finibus tempus. Curabitur feugiat interdum odio, posuere faucibus lacus blandit vel. Vestibulum viverra erat vitae nunc mollis tincidunt. Vestibulum luctus viverra ultricies. Morbi dapibus augue nisl, sed viverra felis ultricies eget. Nam volutpat venenatis tortor, in auctor libero maximus at."),
                Post.Text(3, user2, "Pellentesque vestibulum finibus tempus. Curabitur feugiat interdum odio, posuere faucibus lacus!!"),
                Post.Image(4, user1, "A beautiful image!", "https://picsum.photos/400/300"),
                Post.Text(5, user2, "Post 3"),
                Post.Image(6, user1, "Image 2", "https://picsum.photos/600/300"),
                SectionHeader(7, "Section 2"),
                Post.Text(8, user1, "Post 4"),
                Post.Text(9, user1, "Post 5"),
                Post.Image(10, user2, "Image 3", "https://picsum.photos/500/300"),
                Post.Image(11, user2, "Image 4", "https://picsum.photos/300/300"),
                SectionHeader(12, "Section 3"),
                Post.Text(13, user1, "Post 6"),
                Post.Text(14, user2, "Post 7"),
                Footer
            )
        )
    }

}