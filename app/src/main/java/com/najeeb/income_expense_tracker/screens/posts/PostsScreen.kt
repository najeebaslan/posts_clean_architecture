package com.najeeb.income_expense_tracker.screens.posts

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.najeeb.income_expense_tracker.view_model.PostViewModel

@Composable
fun PostsScreen(onClick: (Int) -> Unit) {
    val vm: PostViewModel = viewModel()

    Scaffold(
        topBar = { AppTopBar() },
        content = { innerPadding ->
            PostListContent(innerPadding, vm, onClick = onClick)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Posts View",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun PostListContent(
    innerPadding: PaddingValues,
    vm: PostViewModel,
    onClick: (Int) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        items(vm.state) { post ->
            PostCard(
                post = post,
                onClick = { id -> onClick(id) },
                onClickFavorite = { id ->
                    vm.toggleFavoriteState(id)
                }

            )
        }
    }
}


