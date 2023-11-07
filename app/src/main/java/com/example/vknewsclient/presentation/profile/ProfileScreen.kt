package com.example.vknewsclient.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vknewsclient.R
import com.example.vknewsclient.presentation.getApplicationComponent

@Composable
fun ProfileScreen() {
    val component = getApplicationComponent()
    val viewModel: ProfileViewModel = viewModel(factory = component.getViewModelFactory())
    val screenState = viewModel.state.collectAsState(ProfileScreenState.Initial)
    ProfileScreenContent(
        screenState = screenState
    )
}

@Composable
private fun ProfileScreenContent(
    screenState: State<ProfileScreenState>
) {
    when(val currentState = screenState.value) {
        is ProfileScreenState.ProfileLoaded -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(124.dp)
                            .clip(CircleShape),
                        model = currentState.profile.profileImageUrl,
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "${currentState.profile.firstName} ${currentState.profile.lastName}",
                    fontSize = 24.sp,
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Birthday: ${currentState.profile.birthdayDate}",
                    fontSize = 16.sp,
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Country: ${currentState.profile.country}",
                    fontSize = 16.sp,
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "City: ${currentState.profile.city}",
                    fontSize = 16.sp,
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Phone: ${currentState.profile.phone}",
                    fontSize = 16.sp,
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Relation: ${currentState.profile.relation}",
                    fontSize = 16.sp,
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Sex: ${currentState.profile.sex}",
                    fontSize = 16.sp,
                )
            }
        }
        is ProfileScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is ProfileScreenState.Initial -> {}
    }
}