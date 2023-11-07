package com.example.vknewsclient.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Profile(
    val profileImageUrl: String,
    val firstName: String,
    val lastName: String,
    val birthdayDate: String,
    val country: String,
    val city: String,
    val phone: String,
    val relation: String,
    val sex: String
)