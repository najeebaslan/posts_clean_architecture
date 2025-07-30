package com.najeeb.income_expense_tracker.features.posts.data.repos

import com.najeeb.income_expense_tracker.features.posts.data.api.PostsApiService
import com.najeeb.income_expense_tracker.features.posts.data.database.PostDAO
import com.najeeb.income_expense_tracker.features.posts.data.models.PostFavoriteState
import com.najeeb.income_expense_tracker.features.posts.data.models.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
  private val postDao: PostDAO,
  private val apiService: PostsApiService,
) {
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

  suspend fun fetchPostById(postId: Int): PostModel = withContext(Dispatchers.IO) {
    return@withContext postDao.getPostById(postId) ?: apiService.getPostById(postId)

  }

  suspend fun toggleFavoritePost(id: Int, state: Boolean) =
    withContext(Dispatchers.IO) {
      postDao.updatePost(PostFavoriteState(isFavorite = !state, id = id))
      return@withContext postDao.getAllPosts()
    }

}