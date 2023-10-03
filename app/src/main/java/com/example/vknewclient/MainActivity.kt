package com.example.vknewclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.vknewclient.ui.theme.MainScreen
import com.example.vknewclient.ui.theme.VkNewClientTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewClientTheme {
                MainScreen(viewModel)
            }
        }
    }
}
