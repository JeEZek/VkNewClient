package com.example.vknewclient.ui.theme


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vknewclient.MainViewModel
import com.example.vknewclient.domain.FeedPost

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val feedPost = viewModel.feedPost.observeAsState(FeedPost())

    Scaffold(
        modifier = Modifier.padding(8.dp),
        bottomBar = { BottomBar() },
        content = { paddingValues ->
            PostCard(
                modifier = Modifier.padding(paddingValues),
                feedPost = feedPost.value,
                onViewsClickListener = viewModel::updateCount,
                onShareClickListener = viewModel::updateCount,
                onCommentClickListener = viewModel::updateCount,
                onLikeClickListener = viewModel::updateCount
            )
        }
    )
}

@Composable
private fun BottomBar() {
    BottomAppBar {
        val selectedItemPosition = rememberSaveable {
            mutableStateOf(0)
        }

        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Favourite,
            NavigationItem.Profile
        )
        items.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = selectedItemPosition.value == index,
                onClick = { selectedItemPosition.value = index },
                icon = { Icon(navigationItem.icon, contentDescription = null) },
                label = { Text(text = stringResource(id = navigationItem.titleResId)) }
            )
        }
    }
}
