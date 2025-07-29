package com.najeeb.income_expense_tracker.view_models

import com.najeeb.income_expense_tracker.data.models.PostModel

data class PostDetailsScreenStates(
  var post: PostModel,
  var isLoading: Boolean,
  var error: String? = null

)
