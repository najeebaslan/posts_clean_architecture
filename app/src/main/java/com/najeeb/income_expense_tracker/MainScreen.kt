package com.najeeb.income_expense_tracker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.najeeb.income_expense_tracker.features.home.presentation.screens.home.HomeScreen
import com.najeeb.income_expense_tracker.features.posts.presentation.screens.post_details.DetailsPostScreen
import com.najeeb.income_expense_tracker.features.posts.presentation.screens.posts.PostsScreen
import com.najeeb.income_expense_tracker.features.posts.presentation.view_models.PostDetailsViewModel
import com.najeeb.income_expense_tracker.features.posts.presentation.view_models.PostViewModel
import com.najeeb.income_expense_tracker.ui.theme.Income_expense_trackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Income_expense_trackerTheme(content = { IncomeExpenseAroundApp() })
    }
  }
}

@Composable
private fun IncomeExpenseAroundApp() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = "posts") {
    composable(route = "home") { HomeScreen() }

    composable(route = "posts") {
      val viewModel: PostViewModel = hiltViewModel()
      PostsScreen(
        onClick = { navController.navigate("posts/$it") },
        viewModel = viewModel
      )
    }

    composable(
      route = "posts/{postId}",
      arguments = listOf(navArgument("postId") { type = NavType.IntType }),
      deepLinks = listOf(
        navDeepLink { uriPattern = "https://jsonplaceholder.typicode.com/posts/{postId}" })
    ) {
      val viewModel: PostDetailsViewModel = hiltViewModel()

      DetailsPostScreen(navController = navController, viewModel = viewModel)
    }
  }
}