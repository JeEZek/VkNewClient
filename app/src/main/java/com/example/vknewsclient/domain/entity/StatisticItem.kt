package com.example.vknewsclient.domain.entity

data class StatisticItem (
    val type: StatisticType,
    val count: Int
)

enum class StatisticType {
    VIEWS, COMMENTS, SHARES, LIKES
}