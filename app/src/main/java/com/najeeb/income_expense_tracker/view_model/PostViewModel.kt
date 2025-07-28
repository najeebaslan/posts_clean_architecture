package com.najeeb.income_expense_tracker.view_model
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.najeeb.income_expense_tracker.data.PostsApiService
import com.najeeb.income_expense_tracker.screens.posts.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel() : ViewModel() {
    var state by mutableStateOf(PostModel.emptyPostModel());
    private var apiService: PostsApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl("https://jsonplaceholder.typicode.com/").build()
        apiService = retrofit.create(PostsApiService::class.java)

    }

    fun getPosts() {
        apiService.getAllPosts().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>?>,
                response: Response<List<PostModel>?>
            ) {
                if (response.isSuccessful) {
                    state = response.body() ?: emptyList()
                } else {
                    state = PostModel.emptyPostModel()
                }
            }

            override fun onFailure(
                call: Call<List<PostModel>?>,
                t: Throwable
            ) {
                state = PostModel.emptyPostModel()
                t.printStackTrace()
                println("Error: ${t.message}")
                println("Error: ${t.cause}")
                println("Error: ${t.localizedMessage}")
                println("Error: ${t.stackTrace}")

            }

        })


    }
}

