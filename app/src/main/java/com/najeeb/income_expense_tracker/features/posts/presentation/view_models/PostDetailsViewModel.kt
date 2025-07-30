package com.najeeb.income_expense_tracker.features.posts.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.najeeb.income_expense_tracker.features.posts.data.models.PostModel
import com.najeeb.income_expense_tracker.features.posts.domain.usecases.GetPostsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
  private val getPostsByIdUseCase: GetPostsByIdUseCase,
  savedStateHandle: SavedStateHandle
  ) : ViewModel() {

  private var _states by mutableStateOf(
    PostDetailsScreenStates(isLoading = true, post = PostModel.emptyPost())
  )
  val states: State<PostDetailsScreenStates> get() = derivedStateOf { _states }

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
    println("-----------------------$postId -----------------")
    getPostById(postId)
  }

  fun getPostById(postId: Int) {
    viewModelScope.launch(errorHandler) {
      val getPost = getPostsByIdUseCase(postId)
      _states = _states.copy(post = getPost, isLoading = false)
    }
  }


}