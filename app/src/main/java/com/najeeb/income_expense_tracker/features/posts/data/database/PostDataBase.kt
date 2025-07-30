package com.najeeb.income_expense_tracker.features.posts.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.najeeb.income_expense_tracker.features.posts.data.models.PostModel

@Database(entities = [PostModel::class], version = 1, exportSchema = false)
abstract class PostDataBase : RoomDatabase() {
  abstract val dao: PostDAO

}