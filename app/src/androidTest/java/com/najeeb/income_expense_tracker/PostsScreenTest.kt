package com.najeeb.income_expense_tracker

import AppTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.najeeb.income_expense_tracker.core.DescriptionsTests
import com.najeeb.income_expense_tracker.features.posts.presentation.posts.PostsScreen
import com.najeeb.income_expense_tracker.features.posts.presentation.view_models.PostScreenState
import org.junit.Rule
import org.junit.Test

class PostsScreenTest {

  @get:Rule
  val testRule: ComposeContentTestRule = createComposeRule()

  @Test
  fun loading_isActive() {
    testRule.setContent {
      AppTheme {
        PostsScreen(
          onClick = { },
          state = PostScreenState(posts = emptyList(), isLoading = true),
          onFavoriteClick = { },
        )
      }
    }
    testRule.onNodeWithContentDescription(DescriptionsTests.POST_LIST_LOADING).assertIsDisplayed()
  }
}