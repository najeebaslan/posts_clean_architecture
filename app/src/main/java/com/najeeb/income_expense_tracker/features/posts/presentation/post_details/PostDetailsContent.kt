package com.najeeb.income_expense_tracker.features.posts.presentation.post_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.najeeb.income_expense_tracker.features.posts.data.models.PostModel


@Composable
fun PostDetailsContent(post: PostModel, modifier: Modifier = Modifier) {
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



