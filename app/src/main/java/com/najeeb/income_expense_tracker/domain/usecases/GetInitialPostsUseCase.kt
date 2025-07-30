package com.najeeb.income_expense_tracker.domain.usecases

import com.najeeb.income_expense_tracker.data.models.PostModel
import com.najeeb.income_expense_tracker.data.repos.PostRepository

class GetInitialPostsUseCase {

  private val postRepository = PostRepository()
  private val getSortedPostsUseCase = GetSortedPostsUseCase()

  suspend operator fun invoke(): List<PostModel> {
    postRepository.loadPosts()
    return getSortedPostsUseCase()
  }
}