package com.example.vknewsclient.presentation.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.data.repository.NewsFeedRepositoryImpl
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.usecases.ChangeLIkeStatusUseCase
import com.example.vknewsclient.domain.usecases.DeletePostUseCase
import com.example.vknewsclient.domain.usecases.GetRecommendationsUseCase
import com.example.vknewsclient.domain.usecases.LoadNextDataUseCase
import com.example.vknewsclient.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("NewsFeedViewModel", "Coroutine handler caught exception: ${throwable.message}")
    }

    private val repository = NewsFeedRepositoryImpl(application)

    private val getRecommendationsUseCase = GetRecommendationsUseCase(repository)
    private val loadNextDataUseCase = LoadNextDataUseCase(repository)
    private val changeLIkeStatusUseCase = ChangeLIkeStatusUseCase(repository)
    private val deletePostUseCase = DeletePostUseCase(repository)

    private val recommendationsFlow = getRecommendationsUseCase()

    private val loadNextDataEvents = MutableSharedFlow<Unit>()
    private val loadNextDataFlow = flow {
        loadNextDataEvents.collect {
            emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationsFlow.value,
                    nextDataIsLoading = true
                )
            )
            loadNextDataUseCase()
        }
    }

    val screenState = recommendationsFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(posts = it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(loadNextDataFlow)

    fun loadNextRecommendations() {
        viewModelScope.launch {
            loadNextDataEvents.emit(Unit)
            loadNextDataUseCase()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLIkeStatusUseCase(feedPost)
        }
    }

    fun removePost(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            deletePostUseCase(feedPost)
        }
    }
}