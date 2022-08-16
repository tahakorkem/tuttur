package com.tahakorkem.tuttur

import android.os.Parcelable
import com.tahakorkem.tuttur.listadapter.common.Identifiable
import kotlinx.parcelize.Parcelize

sealed interface PostItem : Identifiable {
    override val id: Int
}

interface Post : PostItem, Parcelable {
    val author: User
    val content: String?

    @Parcelize
    data class Text(
        override val id: Int,
        override val author: User,
        override val content: String
    ) : Post

    @Parcelize
    data class Image(
        override val id: Int,
        override val author: User,
        override val content: String?,
        val imageUrl: String
    ) : Post
}

data class SectionHeader(
    override val id: Int,
    val title: String
) : PostItem

object Footer : PostItem {
    override val id: Int = Int.MAX_VALUE
    override fun equals(other: Any?) = other is Footer
    override fun hashCode() = 0
}

@Parcelize
data class User(
    val name: String,
    val avatarUrl: String
) : Parcelable