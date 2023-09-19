package com.example.vknewclient.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewclient.R

@Composable
fun PostCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Header()
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "fksfahsfuihaudsfhausdhfuahpdsuhfpiaushfa")
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.post_content_image),
                    contentDescription = "Post image",
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(8.dp))
                Statistics()
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = CircleShape),
            painter = painterResource(id = R.drawable.post_comunity_thumbnail),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "test")
            Text(text = "test")
        }
        Image(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "three points"
        )
    }
}

@Composable
fun Statistics() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "123")
            Image(
                painter = painterResource(id = R.drawable.ic_views_count),
                contentDescription = "Count of views",
            )
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "123")
            Image(
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = "Count of views"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "123")
            Image(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "Count of views"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "123")
            Image(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = "Count of views"
            )
        }
    }
}

@Preview
@Composable
fun PostCardLight() {
    VkNewClientTheme {
        PostCard()
    }
}

@Preview
@Composable
fun PostCardDark() {
    VkNewClientTheme(darkTheme = true) {
        PostCard()
    }
}