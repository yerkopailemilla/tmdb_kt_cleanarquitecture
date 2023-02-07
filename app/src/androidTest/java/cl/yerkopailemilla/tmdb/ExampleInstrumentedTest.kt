package cl.yerkopailemilla.tmdb

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.yerkopailemilla.tmdb.presentation.MainActivity
import cl.yerkopailemilla.tmdb.presentation.screens.home.HomeScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun itemsAddedToScreen() {
        composeTestRule.setContent {
            HomeScreen(navHostController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Title1").assertExists()
        composeTestRule.onNodeWithText("Title2").assertExists()

    }
}