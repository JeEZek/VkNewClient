package com.example.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vknewsclient.data.repository.NewsFeedRepositoryImpl
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val state = getCommentsUseCase(feedPost)
        .map { CommentsScreenState.Comments(feedPost, it) as CommentsScreenState }
        .onStart { emit(CommentsScreenState.Loading) }
}