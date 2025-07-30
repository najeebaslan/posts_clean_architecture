package com.najeeb.income_expense_tracker.features.posts.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
 fun LoadingIndicator(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator()
  }
}