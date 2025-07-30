package com.najeeb.income_expense_tracker.features.posts.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.najeeb.income_expense_tracker.R

@Composable
fun ErrorMessage(message: String, modifier: Modifier, offset: DpOffset = DpOffset(20.dp, 20.dp)) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Image(
      painter = painterResource(id = R.drawable.no_results),
      contentDescription = null,
      modifier = Modifier
        .offset(offset.x, offset.y)
        .size(200.dp)
    )
    Text(
      text = message,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.bodySmall.copy(
        letterSpacing = (-0.2).sp,
        lineHeight = 23.sp,
        color = Color(0xFF95979A).copy(alpha = 0.9f),
        fontSize = 18.sp
      )
    )
  }
}
