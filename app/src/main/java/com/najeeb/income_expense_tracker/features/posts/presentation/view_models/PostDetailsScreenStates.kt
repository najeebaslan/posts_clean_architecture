package com.najeeb.income_expense_tracker.features.posts.presentation.view_models

import com.najeeb.income_expense_tracker.features.posts.data.models.PostModel

data class PostDetailsScreenStates(
  var post: PostModel,
  var isLoading: Boolean,
  var error: String? = null

)
