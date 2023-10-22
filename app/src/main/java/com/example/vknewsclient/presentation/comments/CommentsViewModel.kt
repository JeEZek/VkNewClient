package com.example.vknewsclient.presentation.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.PostComment

class CommentsViewModel(
    feedPost: FeedPost
): ViewModel() {

    private val _state = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val state: LiveData<CommentsScreenState> = _state

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
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