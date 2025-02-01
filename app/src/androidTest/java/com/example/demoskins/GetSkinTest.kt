package com.example.demoskins

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.demoskins.ui.MainScreen
import com.example.demoskins.viewmodel.MainViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetSkinTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalTestApi::class, DelicateCoroutinesApi::class)
    @Test
    fun testGetSkin() {
        val mainViewModel = MainViewModel()
        composeTestRule.setContent {
            MainScreen(mainViewModel)
        }
        GlobalScope.launch {
            mainViewModel.collectSkin()
        }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("input_username", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("input_username").performTextInput("etho")

        composeTestRule.onNodeWithTag("button_search").performClick()

        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("result_username"), 10_000)

        composeTestRule.onNodeWithTag("result_username").assertTextEquals("Etho")
    }
}