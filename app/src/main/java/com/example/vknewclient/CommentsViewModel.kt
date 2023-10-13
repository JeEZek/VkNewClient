package com.example.vknewclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.PostComment
import com.example.vknewclient.ui.theme.CommentsScreenState

class CommentsViewModel: ViewModel() {

    private val _state = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val state: LiveData<CommentsScreenState> = _state

    fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        _state.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
    }
}