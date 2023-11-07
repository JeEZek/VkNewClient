package com.example.vknewsclient.domain.usecases

import com.example.vknewsclient.domain.entity.Profile
import com.example.vknewsclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetProfileInfoUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): Flow<Profile> {
        return repository.getProfileInfo()
    }
}
