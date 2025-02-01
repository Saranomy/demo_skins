package com.example.demoskins.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoskins.ui.theme.DemoSkinsTheme
import com.example.demoskins.viewmodel.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            TextField(
                mainViewModel.searchUsername.value,
                { value ->
                    mainViewModel.searchUsername.value = value
                },
                modifier = Modifier
                    .testTag("input_username")
                    .fillMaxWidth(),
                enabled = !mainViewModel.isLoading.value
            )
            Button(
                onClick = {
                    mainViewModel.getSkin()
                },
                modifier = Modifier
                    .testTag("button_search")
                    .padding(vertical = 16.dp),
                enabled = !mainViewModel.isLoading.value
            ) {
                Text("Search")
            }
            LazyColumn {
                item {
                    mainViewModel.skinResponseUi.value?.username?.let {
                        Text(it, modifier = Modifier.testTag("result_username"))
                    }
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
    val mainViewModel = MainViewModel()
    mainViewModel.searchUsername.value = "Etho"
    DemoSkinsTheme {
        MainScreen(mainViewModel)
    }
}