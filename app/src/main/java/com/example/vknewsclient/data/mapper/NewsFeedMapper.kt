package com.example.vknewsclient.data.mapper

import com.example.vknewsclient.data.model.comments.CommentsResponseDto
import com.example.vknewsclient.data.model.news.NewsFeedResponseDto
import com.example.vknewsclient.data.model.profile.ProfileResponseDto
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.PostComment
import com.example.vknewsclient.domain.entity.Profile
import com.example.vknewsclient.domain.entity.StatisticItem
import com.example.vknewsclient.domain.entity.StatisticType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.absoluteValue

class NewsFeedMapper @Inject constructor() {

    private val relationMap = mapOf(
        0 to "не указано",
        1 to "не женат/не замужем",
        2 to "есть друг/есть подруга",
        3 to "помолвлен/помолвлена",
        4 to "женат/замужем",
        5 to "всё сложно",
        6 to "в активном поиске",
        7 to "влюблён/влюблена",
        8 to "в гражданском браке",
    )
    private val sexMap = mapOf(
        0 to "не указан",
        1 to "мужской",
        2 to "женский",
    )

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: continue
            val feedPost = FeedPost(
                id = post.id,
                communityId = post.communityId,
                communityName = group.name,
                publicationDate = mapTimestampToDate(post.date * 1000),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.VIEWS, count = post.views.count),
                    StatisticItem(type = StatisticType.SHARES, count = post.reposts.count),
                    StatisticItem(type = StatisticType.COMMENTS, count = post.comments.count),
                    StatisticItem(type = StatisticType.LIKES, count = post.likes.count)
                ),
                isLiked = post.likes.userLikes > 0
            )
            result.add(feedPost)
        }
        return result
    }

    fun mapResponseToComments(responseDto: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()

        val comments = responseDto.content.comments
        val profiles = responseDto.content.profiles

        for (comment in comments) {
            //TODO show images and videos in comments
            if (comment.text.isBlank()) continue
            val profile = profiles.find { it.id == comment.authorId.absoluteValue } ?: continue
            val postComment = PostComment(
                id = comment.id,
                authorName = String.format("%s %s", profile.firstName, profile.lastName),
                authorAvatarUrl = profile.photo100,
                commentText = comment.text,
                publicationDate = mapTimestampToDate(comment.date)
            )
            result.add(postComment)
        }
        return result
    }

    fun mapResponseToProfile(responseDto: ProfileResponseDto) = Profile(
        profileImageUrl = responseDto.content.profileImageUrl,
        firstName = responseDto.content.firstName,
        lastName = responseDto.content.lastName,
        birthdayDate = responseDto.content.birthdayDate,
        country = responseDto.content.country.title,
        city = responseDto.content.city.title,
        phone = responseDto.content.phone,
        relation = relationMap[responseDto.content.relation] ?: "не указано",
        sex = sexMap[responseDto.content.sex] ?: "не указан"
    )

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("d MMM в hh:mm", Locale.getDefault()).format(date)
    }
}