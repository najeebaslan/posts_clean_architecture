package com.najeeb.income_expense_tracker.screens.home
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.najeeb.income_expense_tracker.data.models.JobModel

@Composable
fun CardJobs(job: JobModel, onClick: (Int) -> Unit) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,  // Now properly referenced
            contentColor = MaterialTheme.colorScheme.onSurface
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp, color = Color.Gray.copy(alpha = 0.5f)
        ),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Icon(
                Icons.Filled.Place,
                contentDescription = "icon place",
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    job.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    job.description,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant  // Secondary text color

                )
            }

            FavoriteIconButton(onClick, job.isFavorite, job.id)
        }
    }
}


@Composable
fun FavoriteIconButton(onClick: (Int) -> Unit, isFavorite: Boolean, jobId: Int) {

    IconButton(
        onClick = { onClick(jobId) },
        content = {
            Icon(
                if (isFavorite) {
                    Icons.Filled.Favorite;
                } else {
                    Icons.Outlined.FavoriteBorder;
                },
                contentDescription = "Icon Favorite",
                tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
            )
        })
}

