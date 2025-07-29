package com.najeeb.income_expense_tracker.screens.posts

import com.najeeb.income_expense_tracker.component.LoadingIndicator
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.najeeb.income_expense_tracker.component.ErrorMessage
import com.najeeb.income_expense_tracker.data.models.PostModel
import com.najeeb.income_expense_tracker.view_models.PostDetailsViewModel

@Composable
fun DetailsPostScreen(navController: NavController) {
  val viewModel: PostDetailsViewModel = viewModel()
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

@Composable
private fun PostDetailsContent(post: PostModel, modifier: Modifier = Modifier) {
  Card(
    modifier = modifier
      .padding(16.dp)
      .fillMaxWidth(),
    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = "Post #${post.id} by User ${post.userId}",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = post.title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = post.body,
        style = MaterialTheme.typography.bodyLarge,
        lineHeight = 20.sp
      )
      Spacer(modifier = Modifier.height(12.dp))

    }
  }
}



