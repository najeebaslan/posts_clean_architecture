package com.najeeb.income_expense_tracker.features.posts.presentation.screens.post_details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.najeeb.income_expense_tracker.core.component.ErrorMessage
import com.najeeb.income_expense_tracker.core.component.LoadingIndicator
import com.najeeb.income_expense_tracker.features.posts.presentation.view_models.PostDetailsViewModel

@Composable
fun DetailsPostScreen(navController: NavController,viewModel:PostDetailsViewModel ) {

  val state = viewModel.states.value;
  Scaffold(
    modifier = Modifier,
    topBar = { PostDetailsTopBar(navController = navController) },
    content = { innerPadding ->
      when {
        state.isLoading -> LoadingIndicator(
          modifier = Modifier.fillMaxSize()
        )

        state.error != null -> ErrorMessage(
          message = state.error!!,
          modifier = Modifier.fillMaxSize()
        )

        else -> PostDetailsContent(
          post = state.post,
          modifier = Modifier.padding(innerPadding)
        )

      }
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostDetailsTopBar(navController: NavController, modifier: Modifier = Modifier) {
  TopAppBar(
    title = {
      Text(
        text = "Post Details",
        style = MaterialTheme.typography.titleLarge
      )
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primary,
      titleContentColor = MaterialTheme.colorScheme.onPrimary
    ),
    navigationIcon = {
      IconButton(onClick = { navController.popBackStack() }) {
        Icon(
          imageVector = Icons.Filled.ArrowBack,
          contentDescription = "Back",
          tint = MaterialTheme.colorScheme.onPrimary
        )
      }
    },
    modifier = modifier
  )
}
