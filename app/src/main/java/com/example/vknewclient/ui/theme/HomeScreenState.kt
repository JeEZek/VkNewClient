package com.example.vknewclient.ui.theme

import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.PostComment

sealed class HomeScreenState {
    object Initial : HomeScreenState()

    data class Posts(val posts: List<FeedPost>) : HomeScreenState()
    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>) : HomeScreenState()
}
