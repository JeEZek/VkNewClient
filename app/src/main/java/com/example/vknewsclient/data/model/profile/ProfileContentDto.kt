package com.example.vknewsclient.data.model.profile

import com.google.gson.annotations.SerializedName

data class ProfileContentDto(
    @SerializedName("photo_200") val profileImageUrl: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("bdate") val birthdayDate: String,
    @SerializedName("country") val country: CountryDto,
    @SerializedName("city") val city: CityDto,
    @SerializedName("phone") val phone: String,
    @SerializedName("relation") val relation: Int,
    @SerializedName("sex") val sex: Int
)
