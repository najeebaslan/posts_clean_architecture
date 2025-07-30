package com.najeeb.income_expense_tracker.features.posts.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.najeeb.income_expense_tracker.features.posts.domain.usecases.GetInitialPostsUseCase
import com.najeeb.income_expense_tracker.features.posts.domain.usecases.ToggleFavoritePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
@HiltViewModel
class PostViewModel @Inject constructor(
 private val getInitialPostsUseCase : GetInitialPostsUseCase,
 private val toggleFavoritePostUseCase : ToggleFavoritePostUseCase,
) : ViewModel() {

  private var _postsState by mutableStateOf(
    PostScreenState(posts = emptyList(), isLoading = true)
  )
  val postsState: State<PostScreenState> get() = derivedStateOf { _postsState }

  private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
    _postsState = _postsState.copy(
      error = when (throwable) {
        is IOException -> "Network error. Please check your internet connection."
        is HttpException -> "Server error: ${throwable.code()}. Please try again later."
        else -> "An unexpected error occurred: ${throwable.localizedMessage ?: "Unknown error"}"
      },
      isLoading = false
    )
  }

  init {
    getPosts()
  }


  fun getPosts() {
    viewModelScope.launch(coroutineExceptionHandler) {
      val receivedPosts = getInitialPostsUseCase()
      _postsState = _postsState.copy(posts = receivedPosts, isLoading = false)
    }
  }


  fun toggleFavoriteState(postId: Int) {
    val currentPosts = _postsState.posts.toMutableList()
    val index = currentPosts.indexOfFirst { it.id == postId }

    viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
      if (index != -1) {
        val updatedPost = toggleFavoritePostUseCase(postId, currentPosts[index].isFavorite)

        _postsState = _postsState.copy(posts = updatedPost)
      }
    }
  }
}
