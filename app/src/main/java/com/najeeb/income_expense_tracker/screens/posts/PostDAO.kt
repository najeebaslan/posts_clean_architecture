package com.najeeb.income_expense_tracker.screens.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PostDAO {
    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<PostModel>

    @Query("SELECT * FROM posts WHERE id = :id")
    suspend fun getPostById(id: Int): PostModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPosts(post: List<PostModel>)

    @Update(entity = PostModel::class)
    suspend fun updatePost(postFavoriteState: PostFavoriteState)

    @Delete
    suspend fun deletePost(post: PostModel)

}