package com.shreyas.go_train_schedule.view

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shreyas.go_train_schedule.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testBottomNavigationBar() {
        // Check if the Home screen is displayed by default
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.title_arrivals))
            .assertExists()

        // Navigate to Departures screen
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.title_union_departures))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.title_union_departures))
            .assertExists()

        // Navigate to Pricing screen
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.title_price))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.title_price))
            .assertExists()

        // Navigate to Info screen
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.title_info))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.title_info))
            .assertExists()
    }
}