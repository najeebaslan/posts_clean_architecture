package com.najeeb.income_expense_tracker.features.posts.presentation.screens.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.najeeb.income_expense_tracker.core.component.ErrorMessage
import com.najeeb.income_expense_tracker.features.posts.data.models.PostModel
import com.najeeb.income_expense_tracker.features.posts.presentation.view_models.PostScreenState
import com.najeeb.income_expense_tracker.features.posts.presentation.view_models.PostViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(onClick: (Int) -> Unit, viewModel: PostViewModel) {

  Scaffold(
    contentColor = Color.White,
    topBar = { PostsTopAppBar() }
  ) { paddingValues ->
    PostsContent(
      onClick = onClick,
      state = viewModel.postsState.value,
      onFavoriteClick = { viewModel.toggleFavoriteState(it) },
      modifier = Modifier.padding(paddingValues)
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostsTopAppBar() {
  TopAppBar(
    title = { Text("Posts View") },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primary,
      titleContentColor = MaterialTheme.colorScheme.onPrimary
    )
  )
}

@Composable
private fun PostsContent(
  state: PostScreenState,
  onClick: (Int) -> Unit,
  onFavoriteClick: (Int) -> Unit,
  modifier: Modifier,
) {
  when {
    state.error != null -> ErrorMessage(
      message = state.error!!,
      modifier = modifier,
    )

    state.isLoading -> PostCardShimmer(modifier)

    else -> PostsList(
      posts = state.posts,
      onClick = onClick,
      onFavoriteClick = onFavoriteClick,
      modifier = modifier
    )
  }
}

@Composable
private fun PostsList(
  posts: List<PostModel>,
  onClick: (Int) -> Unit,
  onFavoriteClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 20.dp),
    contentPadding = PaddingValues(vertical = 20.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(posts) { post ->
      PostCard(
        post = post,
        onClick = { onClick(post.id) },
        onClickFavorite = { onFavoriteClick(post.id) }
      )
    }
  }
}