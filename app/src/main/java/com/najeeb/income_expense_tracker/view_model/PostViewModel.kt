package com.najeeb.income_expense_tracker.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.najeeb.income_expense_tracker.app.IncomeExpenseApplication
import com.najeeb.income_expense_tracker.data.PostsApiService
import com.najeeb.income_expense_tracker.screens.posts.PostDataBase
import com.najeeb.income_expense_tracker.screens.posts.PostFavoriteState
import com.najeeb.income_expense_tracker.screens.posts.PostModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel() : ViewModel() {
    var state by mutableStateOf(PostModel.emptyPosts());
    val getDAO = PostDataBase.getDaoInstance(IncomeExpenseApplication.getAppContext())
    private var apiService: PostsApiService
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }


    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl(BASE_URL).build()

        apiService = retrofit.create(PostsApiService::class.java)
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch(errorHandler) {
            state = handelGetPosts()

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

