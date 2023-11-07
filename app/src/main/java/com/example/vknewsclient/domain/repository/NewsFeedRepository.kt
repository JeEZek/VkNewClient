package com.example.vknewsclient.domain.repository

import com.example.vknewsclient.domain.entity.AuthState
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.PostComment
import com.example.vknewsclient.domain.entity.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getRecommendations(): StateFlow<List<FeedPost>>

    fun getComments(feedPost: FeedPost): Flow<List<PostComment>>

    fun getProfileInfo(): Flow<Profile>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun changeLikeStatus(feedPost: FeedPost)

    suspend fun deletePost(feedPost: FeedPost)

}