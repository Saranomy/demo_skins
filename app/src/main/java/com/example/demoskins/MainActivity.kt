package com.example.demoskins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.demoskins.ui.MainScreen
import com.example.demoskins.ui.theme.DemoSkinsTheme
import com.example.demoskins.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainViewModel.skinResponseFlow.collect { skinResponse ->
                        if (skinResponse != null) {
                            mainViewModel.skinResponseUi.value = skinResponse
                        }
                    }
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            DemoSkinsTheme {
                MainScreen(mainViewModel = mainViewModel)
            }
        }
    }
}