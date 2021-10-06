package com.steve.networksocial.presentation.splash

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.steve.networksocial.MainActivity
import com.steve.networksocial.presentation.util.Screen
import com.steve.networksocial.ui.theme.NetworkSocialTheme
import com.steve.networksocial.util.Constants
import com.steve.networksocial.util.Constants.SPLASH_SCREEN_DURATION
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {
    @ExperimentalMaterialApi
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @RelaxedMockK
    lateinit var navController: NavController

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }


    @ExperimentalMaterialApi
    @Test
    fun splashScreen_displaysAndDisappears() = testDispatcher.runBlockingTest {
        composeTestRule.setContent {
            NetworkSocialTheme {
                SplashScreen(
                    navController = navController,
                    dispatcher = testDispatcher


                    )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Logo")
            .assertExists()

        advanceTimeBy(Constants.SPLASH_SCREEN_DURATION)

        verify {
            navController.popBackStack()
            navController.navigate(Screen.LoginScreen.route)
        }
    }
}
