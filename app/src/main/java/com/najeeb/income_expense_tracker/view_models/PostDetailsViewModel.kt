package com.najeeb.income_expense_tracker.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.najeeb.income_expense_tracker.core.Constants
import com.najeeb.income_expense_tracker.data.api.PostsApiService
import com.najeeb.income_expense_tracker.data.models.PostModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostDetailsViewModel(private val stateHandel: SavedStateHandle) : ViewModel() {
    var state by mutableStateOf(PostModel.emptyPost());
    private var apiService: PostsApiService
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl(Constants.BASE_URL).build()
        apiService = retrofit.create(PostsApiService::class.java)
        val getPostId = stateHandel.get<Int>("postId") ?: 0
        getPostById(getPostId)
    }

    fun getPostById(postId: Int) = viewModelScope.launch(errorHandler)
    {
        state = withContext(Dispatchers.IO) {
            apiService.getPostById(postId)
        }

    }


}

