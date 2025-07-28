package com.najeeb.income_expense_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.najeeb.income_expense_tracker.screens.auth.LoginScreen
import com.najeeb.income_expense_tracker.screens.home.HomeScreen
import com.najeeb.income_expense_tracker.screens.posts.DetailsPostScreen
import com.najeeb.income_expense_tracker.screens.posts.PostsScreen
import com.najeeb.income_expense_tracker.ui.theme.Income_expense_trackerTheme

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
        composable(route = "login") { LoginScreen() }
        composable(route = "home") { HomeScreen() }
        composable(route = "posts") {
            PostsScreen(onClick = {
                navController.navigate("posts/$it")
            })
        }

        composable(
            route = "posts/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType }),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://jsonplaceholder.typicode.com/posts/{postId}"
                }
            )

        ) {
            DetailsPostScreen(navController =navController )
        }
    }
}