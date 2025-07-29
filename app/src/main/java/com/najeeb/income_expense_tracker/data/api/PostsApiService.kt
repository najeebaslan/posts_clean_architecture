package com.najeeb.income_expense_tracker.data.api

import com.najeeb.income_expense_tracker.data.models.PostModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiService {
    @GET("posts")
    suspend fun getAllPosts(): List<PostModel>


    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): PostModel

}