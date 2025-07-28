package com.najeeb.income_expense_tracker.data

import com.najeeb.income_expense_tracker.screens.posts.PostModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApiService {
    @GET("posts")
    suspend fun getAllPosts(): List<PostModel>


    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): PostModel

}