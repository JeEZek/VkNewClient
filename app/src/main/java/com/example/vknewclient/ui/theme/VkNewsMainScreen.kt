package com.example.vknewclient.ui.theme


import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewclient.domain.FeedPost

@Composable
fun MainScreen() {

    val feedPost = remember {
        mutableStateOf(FeedPost())
    }

    Scaffold(
        modifier = Modifier.padding(8.dp),
        bottomBar = { BottomBar() },
        content = { paddingValues ->
            PostCard(
                modifier = Modifier.padding(paddingValues),
                feedPost = feedPost.value,
                onStatisticItemClickListener = { newItem ->
                    val oldStatistics = feedPost.value.statistics
                    val newStatistics = oldStatistics.toMutableList().apply {
                        replaceAll { oldItem ->
                            if (oldItem.type == newItem.type) {
                                oldItem.copy(count = oldItem.count + 1)
                            } else {
                                oldItem
                            }
                        }
                    }
                    feedPost.value = feedPost.value.copy(statistics = newStatistics)
                }
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
                label = { Text(text = stringResource(id = navigationItem.titleResId)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}
