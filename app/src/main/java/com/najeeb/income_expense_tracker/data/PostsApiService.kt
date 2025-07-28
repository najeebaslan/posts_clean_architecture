package com.najeeb.income_expense_tracker.data

import com.najeeb.income_expense_tracker.screens.posts.PostModel
import retrofit2.Call
import retrofit2.http.GET

interface PostsApiService {
    @GET("posts")
    fun getAllPosts(): Call<List<PostModel>>
}