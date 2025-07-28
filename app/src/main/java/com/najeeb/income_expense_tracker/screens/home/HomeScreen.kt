@file:OptIn(ExperimentalMaterial3Api::class)

package com.najeeb.income_expense_tracker.screens.home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.najeeb.income_expense_tracker.ui.theme.Income_expense_trackerTheme
import com.najeeb.income_expense_tracker.view_model.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
class HomeScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Income_expense_trackerTheme {
                Scaffold(
                    topBar = { AppTopBar() },
                    content = { innerPadding -> JobListContent(innerPadding) }
                )
            }
        }
    }
}

@Composable
private fun AppTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Home", // Consider using string resources
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
private fun JobListContent(innerPadding: PaddingValues) {
    val vm: HomeViewModel = viewModel()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        items(vm.state) { job ->
            CardJobs(job) { vm.toggleFavoriteState(it) }
        }
    }
}