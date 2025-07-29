package com.najeeb.income_expense_tracker.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.najeeb.income_expense_tracker.IncomeExpenseApplication
import com.najeeb.income_expense_tracker.core.Constants
import com.najeeb.income_expense_tracker.data.api.PostsApiService
import com.najeeb.income_expense_tracker.data.database.PostDataBase
import com.najeeb.income_expense_tracker.data.models.PostModel
import com.najeeb.income_expense_tracker.data.repos.PostRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class PostDetailsViewModel(
  private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private var _states by mutableStateOf(
    PostDetailsScreenStates(isLoading = true, post = PostModel.emptyPost())
  )
  val states: State<PostDetailsScreenStates> get() = derivedStateOf { _states }

  private val postRepo = PostRepository()

  private val errorHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
    _states = _states.copy(
      error = when (throwable) {
        is IOException -> "Network error. Please check your internet connection."
        is HttpException -> "Server error: ${throwable.code()}. Please try again later."
        else -> "An unexpected error occurred: ${throwable.localizedMessage ?: "Unknown error"}"
      },
      isLoading = false
    )
  }

  init {
    val postId = savedStateHandle.get<Int>("postId") ?: 0
    getPostById(postId)
  }

  fun getPostById(postId: Int) {
    viewModelScope.launch(errorHandler) {
      val getPost = postRepo.fetchPostById(postId)
      _states = _states.copy(post = getPost, isLoading = false)
    }
  }


}