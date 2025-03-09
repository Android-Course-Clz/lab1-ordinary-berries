package me.ordinary_berries.lab1.dto

import java.time.Instant

data class PostDto(
    val profileName: String,
    val createdAt: Instant,
    val postBody: String,
    val likesCount: Int,
    val commentsCount: Int,
    val profileImageUrl: String,
    val postImageUrl: String?,
    val isLiked: Boolean,
)
