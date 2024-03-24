package com.app.pixabay.android.presenter.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.pixabay.android.presenter.search.dummy.Dummy
import com.app.pixabay.android.presenter.search.ui.SearchUI
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchUIKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testSearchResultsDisplayed() {
        // Given
        var searchQuery = "Fruits"
        val itemsList = Dummy.searchResultDomainModel

        // When
        composeTestRule.setContent {
            SearchUI(
                searchQuery = { searchQuery },
                onQueryChange = { searchQuery = it },
                { itemsList },
                {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Fruits").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cancel").assertIsDisplayed()
    }

    @Test
    fun testScrollToTag() {
        // Given
        val itemsList = Dummy.searchResultDomainModel

        // When
        composeTestRule.setContent {
            SearchUI(
                searchQuery = { "" },
                onQueryChange = { },
                { itemsList },
                {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("tag8").performScrollTo()
        composeTestRule.onNodeWithText("tag8").assertIsDisplayed()
    }
//
    @Test
    fun testSearchDetailDisplayed()  {
        // Given
        val itemsList = Dummy.searchResultDomainModel


        // When
        composeTestRule.setContent {
            SearchUI(
                searchQuery = { "" },
                onQueryChange = { },
                { itemsList },
                {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("tag3").performClick()
        composeTestRule.onNodeWithText("YES").assertIsDisplayed()

    }
}