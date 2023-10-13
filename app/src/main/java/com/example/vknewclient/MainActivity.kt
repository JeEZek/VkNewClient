package com.example.vknewclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vknewclient.ui.theme.MainScreen
import com.example.vknewclient.ui.theme.VkNewClientTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewClientTheme {
                MainScreen()
            }
        }
    }
}
