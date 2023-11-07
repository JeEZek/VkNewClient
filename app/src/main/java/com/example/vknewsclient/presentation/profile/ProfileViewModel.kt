package com.example.vknewsclient.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.domain.usecases.GetProfileInfoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.delayFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getProfileInfoUseCase: GetProfileInfoUseCase
) : ViewModel() {

    val state = getProfileInfoUseCase()
        .map { ProfileScreenState.ProfileLoaded(profile = it) as ProfileScreenState }
        .onStart { emit(ProfileScreenState.Loading) }
}