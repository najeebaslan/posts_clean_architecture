package com.najeeb.income_expense_tracker.domain.usecases

import com.najeeb.income_expense_tracker.data.models.PostModel
import com.najeeb.income_expense_tracker.data.repos.PostRepository

class GetSortedPostsUseCase {

  private val postRepository = PostRepository()
  suspend operator fun invoke(): List<PostModel> {
    return postRepository.getAllPosts().sortedByDescending { it.isFavorite }
  }

}