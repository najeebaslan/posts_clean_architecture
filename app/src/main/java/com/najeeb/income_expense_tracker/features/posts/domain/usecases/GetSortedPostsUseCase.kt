package com.najeeb.income_expense_tracker.features.posts.domain.usecases

import com.najeeb.income_expense_tracker.features.posts.data.models.PostModel
import com.najeeb.income_expense_tracker.features.posts.data.repos.PostRepository
import javax.inject.Inject

class GetSortedPostsUseCase @Inject constructor(
  private val postRepository: PostRepository,
) {
  suspend operator fun invoke(): List<PostModel> {
    return postRepository.getAllPosts().sortedBy { it.id }
  }
}