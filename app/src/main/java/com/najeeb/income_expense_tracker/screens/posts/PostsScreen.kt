package com.najeeb.income_expense_tracker.screens.posts

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
import com.najeeb.income_expense_tracker.ui.theme.Income_expense_trackerTheme
import com.najeeb.income_expense_tracker.view_model.PostViewModel

//@OptIn(ExperimentalMaterial3Api::class)
class PostsScreen : ComponentActivity() {
    val vm by viewModels<PostViewModel>()

    override fun onStart() {
        super.onStart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        vm.getPosts()
//        Toast.makeText(
//            this,
//            "Posts Loaded", /* duration = */
//            Toast.LENGTH_SHORT
//        ).show()
        setContent {
            Income_expense_trackerTheme {
                Scaffold(
                    topBar = { AppTopBar() },
                    content = { innerPadding -> PostListContent(innerPadding, vm) }
                )
            }
        }
    }
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
private fun PostListContent(innerPadding: PaddingValues, vm: PostViewModel) {
    val context = LocalContext.current
    Toast.makeText(
        context,
            "Posts Loaded", /* duration = */
            Toast.LENGTH_SHORT
        ).show()
    vm.getPosts()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        items(vm.state) { post ->
            PostCard(post) {
                Toast.makeText(
                    context,
                    "Clicked on $it", /* duration = */
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}


