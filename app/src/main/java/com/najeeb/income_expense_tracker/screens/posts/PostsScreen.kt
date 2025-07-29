package com.najeeb.income_expense_tracker.screens.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.najeeb.income_expense_tracker.view_models.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(onClick: (Int) -> Unit) {
  val vm: PostViewModel = viewModel()

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Posts View", color = MaterialTheme.colorScheme.onPrimary) },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
      )
    }
  ) { padding ->
    if (vm.isLoading.value) {
      Box(Modifier
        .fillMaxSize()
        .padding(padding), Alignment.Center) {
        CircularProgressIndicator()
      }
    } else {
      LazyColumn(
        Modifier
          .fillMaxSize()
          .padding(padding)
          .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        items(vm.state) { post ->
          PostCard(
            post = post,
            onClick = { onClick(post.id) },
            onClickFavorite = { vm.toggleFavoriteState(post.id) }
          )
        }
      }
    }
  }
}