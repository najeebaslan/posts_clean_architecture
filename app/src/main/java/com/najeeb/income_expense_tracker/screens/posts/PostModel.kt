package com.najeeb.income_expense_tracker.screens.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class PostModel(
    @SerializedName("userId")
    val userId: Int,
    @PrimaryKey()
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false,
) {


    companion object {
        fun emptyPosts(): List<PostModel> {
            return listOf(PostModel(1, 1, "title1", "body1"))
        }

        fun emptyPost(): PostModel {
            return PostModel(1, 1, "title1", "body1")
        }

    }
}


data class PostFavoriteState(
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false,
    @ColumnInfo("id")
    val id: Int

)