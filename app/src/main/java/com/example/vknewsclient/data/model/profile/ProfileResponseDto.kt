package com.example.vknewsclient.data.model.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponseDto (
    @SerializedName("response") val content: ProfileContentDto
)