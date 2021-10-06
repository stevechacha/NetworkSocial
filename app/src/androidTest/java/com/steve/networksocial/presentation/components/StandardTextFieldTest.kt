package com.steve.networksocial.presentation.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.steve.networksocial.MainActivity
import com.steve.networksocial.util.TestTags.PASSWORD_TOGGLE
import com.steve.networksocial.util.TestTags.STANDARD_TEXT_FIELD
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class StandardTextFieldTest {

    @ExperimentalMaterialApi
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalMaterialApi
    @Test
    fun enterTooLongString_maxLengthNotExceeded() {
        composeTestRule.setContent {
            var text by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                StandardTextField(
                    text = text,
                    onValueChange = {
                        text = it
                    },
                    maxLength = 5,
                    modifier = Modifier
                        .semantics {
                            testTag = STANDARD_TEXT_FIELD
                        }
                )
            }
        }

        val expectedString = "aaaaa"
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextClearance()
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput(expectedString)
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput("a")

        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .assertTextEquals(expectedString)
    }

    @ExperimentalMaterialApi
    @Test
    fun enterPassword_toggleVisibility_passwordVisible() {
        composeTestRule.setContent {
            var text by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                StandardTextField(
                    text = text,
                    onValueChange = {
                        text = it
                    },
                    maxLength = 5,
                    keyboardType = KeyboardType.Password
                )
            }
        }
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput("aaaaa")

        composeTestRule
            .onNodeWithTag(PASSWORD_TOGGLE)
    }

}