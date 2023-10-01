package com.example.vknewclient.domain

import com.example.vknewclient.R

data class FeedPost (
    val communityName: String = "test",
    val publicationDate: String = "00:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "testtesttesttesttesttesttest",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, count = 123),
        StatisticItem(type = StatisticType.SHARES, count = 123),
        StatisticItem(type = StatisticType.COMMENTS, count = 12),
        StatisticItem(type = StatisticType.LIKES, count = 3)
    )
)