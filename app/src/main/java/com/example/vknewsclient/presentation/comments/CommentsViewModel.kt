package com.example.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vknewsclient.data.repository.NewsFeedRepository
import com.example.vknewsclient.domain.FeedPost
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepository(application)

    val state = repository.getComments(feedPost)
        .map { CommentsScreenState.Comments(feedPost, it) as CommentsScreenState }
        .onStart { emit(CommentsScreenState.Loading) }
}