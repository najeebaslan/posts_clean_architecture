package com.najeeb.income_expense_tracker.data.repos

import com.najeeb.income_expense_tracker.IncomeExpenseApplication
import com.najeeb.income_expense_tracker.core.RetrofitFactory.createPostsRetrofitFactory
import com.najeeb.income_expense_tracker.data.api.PostsApiService
import com.najeeb.income_expense_tracker.data.database.PostDataBase
import com.najeeb.income_expense_tracker.data.models.PostFavoriteState
import com.najeeb.income_expense_tracker.data.models.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {
  // Lazily initialize DAO to ensure context is available
  private val postDao by lazy {
    PostDataBase.getDaoInstance(IncomeExpenseApplication.getAppContext())
  }

  private val apiService: PostsApiService by lazy {
    createPostsRetrofitFactory(PostsApiService::class.java)
  }

  suspend fun loadPosts() = withContext(Dispatchers.IO) {
    try {
      updateLocalData()
      postDao.getAllPosts()
    } catch (e: Exception) {
      if (postDao.getAllPosts().isEmpty()) {
        throw Exception("No data available. Please connect to the internet to fetch data or try again.")
      }
    }
  }

  suspend fun getAllPosts(): List<PostModel> = withContext(Dispatchers.IO) {
    return@withContext postDao.getAllPosts()
  }

  private suspend fun updateLocalData() {
    val newPosts = apiService.getAllPosts()
    val favoritePostIds = postDao.getAllFavoritePosts().map { it.id }.toSet()

    val postsToInsert = newPosts.map { apiPost ->
      if (favoritePostIds.contains(apiPost.id)) {
        apiPost.copy(isFavorite = true)
      } else {
        apiPost
      }
    }
    postDao.addAllPosts(postsToInsert)
  }

  suspend fun fetchPostById(postId: Int): PostModel {
    return withContext(Dispatchers.IO) {
      return@withContext postDao.getPostById(postId) ?: apiService.getPostById(postId)
    }
  }

  suspend fun toggleFavoritePost(id: Int, state: Boolean) =
    withContext(Dispatchers.IO) {
      postDao.updatePost(PostFavoriteState(isFavorite = !state, id = id))
      return@withContext postDao.getAllPosts()
    }

}