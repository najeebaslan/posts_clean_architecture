package com.najeeb.income_expense_tracker.screens.posts

import com.google.gson.annotations.SerializedName

data class PostModel(
     @SerializedName("userId")
    val userId: Int,
     @SerializedName("id")
    val id: Int,
     @SerializedName("title")
    val title: String,
     @SerializedName("body")
    val body: String,
) {


    companion object {
        fun emptyPostModel(): List<PostModel> {
            return listOf(PostModel(1, 1, "title1", "body1"))
        }

    }
}