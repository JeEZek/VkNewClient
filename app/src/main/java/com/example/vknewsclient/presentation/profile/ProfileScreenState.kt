package com.example.vknewsclient.presentation.profile

import com.example.vknewsclient.domain.entity.Profile

sealed class ProfileScreenState {

    object Initial : ProfileScreenState()

    object Loading: ProfileScreenState()

    data class ProfileLoaded(
        val profile: Profile
    ) : ProfileScreenState()
}