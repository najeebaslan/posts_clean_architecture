package com.najeeb.income_expense_tracker.features.posts.domain.usecases

import com.najeeb.income_expense_tracker.features.posts.data.models.PostModel
import com.najeeb.income_expense_tracker.features.posts.data.repos.PostRepository
import javax.inject.Inject

class ToggleFavoritePostUseCase @Inject constructor(
  private val postRepository: PostRepository,
  private val getSortedPostsUseCase: GetSortedPostsUseCase
) {

  suspend operator fun invoke(id: Int, newState: Boolean): List<PostModel> {
    postRepository.toggleFavoritePost(id = id, state = newState)
    return getSortedPostsUseCase();
  }
}