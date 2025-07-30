package com.najeeb.income_expense_tracker.domain.usecases

import com.najeeb.income_expense_tracker.data.models.PostModel
import com.najeeb.income_expense_tracker.data.repos.PostRepository

class ToggleFavoritePostUseCase {

  private val postRepository = PostRepository()

  private val getSortedPostsUseCase = GetSortedPostsUseCase()

  suspend operator fun invoke(id: Int, newState: Boolean): List<PostModel> {
    postRepository.toggleFavoritePost(id = id, state = newState)
    return getSortedPostsUseCase();
  }
}