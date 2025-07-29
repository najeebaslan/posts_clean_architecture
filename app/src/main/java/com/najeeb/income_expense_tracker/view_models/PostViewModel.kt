package com.najeeb.income_expense_tracker.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.najeeb.income_expense_tracker.IncomeExpenseApplication
import com.najeeb.income_expense_tracker.data.api.PostsApiService
import com.najeeb.income_expense_tracker.data.database.PostDataBase
import com.najeeb.income_expense_tracker.data.models.PostFavoriteState
import com.najeeb.income_expense_tracker.data.models.PostModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel() : ViewModel() {
  var state by mutableStateOf(emptyList<PostModel>());
  val getDAO = PostDataBase.getDaoInstance(IncomeExpenseApplication.getAppContext())
  private var apiService: PostsApiService
  private val errorHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
  }

  val isLoading = mutableStateOf<Boolean>(false)


  private val _errorResponse = MutableLiveData<String>()
  val errorResponse: LiveData<String>
    get() = _errorResponse

  init {
    val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
      GsonConverterFactory.create()
    ).baseUrl(BASE_URL).build()

    apiService = retrofit.create(PostsApiService::class.java)
    getPosts()
  }

  fun getPosts() {
    isLoading.value = true
    viewModelScope.launch(errorHandler) {
      state = handelGetPosts()
      isLoading.value = false

    }
  }

  private suspend fun handelGetPosts() =
    withContext(Dispatchers.IO) {
      try {
        val posts = apiService.getAllPosts()
        getDAO.addAllPosts(posts)
        return@withContext posts
      } catch (e: Exception) {
        return@withContext getDAO.getAllPosts()
      }
    }

  fun toggleFavoriteState(postId: Int) {
    val posts = state.toMutableList();
    val indexPost = posts.indexOfFirst { it.id == postId }
    posts[indexPost] = posts[indexPost].copy(isFavorite = !posts[indexPost].isFavorite)

    viewModelScope.launch(errorHandler) {
      updatePost(posts[indexPost])
      state = posts

    }
  }

  suspend fun updatePost(post: PostModel) {
    getDAO.updatePost(PostFavoriteState(post.isFavorite, post.id))
  }

  companion object {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
  }
}

