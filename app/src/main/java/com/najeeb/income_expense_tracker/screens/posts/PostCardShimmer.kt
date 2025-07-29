package com.najeeb.income_expense_tracker.screens.posts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer


@Composable
fun PostCardShimmer(
  modifier: Modifier ,
  shimmer: Shimmer = rememberShimmer(ShimmerBounds.View)
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize(),
    contentPadding = PaddingValues(vertical = 20.dp, horizontal = 20.dp),
    verticalArrangement = Arrangement.spacedBy(5.dp)
  ) {

    items(10){
      Card(
        colors = CardDefaults.cardColors(
          containerColor = Color.White,
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
            .fillMaxWidth()
            .shimmer(shimmer)
        ) {
          Box(
            modifier = Modifier
              .size(24.dp)
              .clip(CircleShape)
              .background(Color.LightGray)
          )

          Column(
            modifier = Modifier
              .weight(1f)
              .padding(start = 8.dp, end = 9.dp)
          ) {
            Box(
              modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .height(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
              modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(4.dp))


            Box(
              modifier = Modifier
                .fillMaxWidth(fraction = 0.6f)
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray)
            )
          }


          Box(
            modifier = Modifier
              .size(24.dp)
              .clip(CircleShape)
              .background(Color.LightGray)
          )
        }
      }
    }
  }
}