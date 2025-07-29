package com.najeeb.income_expense_tracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.najeeb.income_expense_tracker.data.models.PostModel
import com.najeeb.income_expense_tracker.data.database.PostDAO

@Database(entities = [PostModel::class], version = 1, exportSchema = false)
abstract class PostDataBase : RoomDatabase() {
    abstract val dao: PostDAO

    companion object {
        const val DATABASE_NAME = "posts_db"

        @Volatile //==>Makes modifying the variable safe in a multi-threaded environment.
        private var postInstance: PostDAO? = null
        private fun buildDatabase(context: Context): PostDataBase =
            Room.databaseBuilder(
                context.applicationContext,
                PostDataBase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration(true).build()


        fun getDaoInstance(context: Context): PostDAO {
            ///===> [synchronized] This prevents creating more than one reference to create the database.
            synchronized(this) {
                if (postInstance == null) {
                    postInstance = buildDatabase(context).dao
                }
                return postInstance as PostDAO
            }

        }
    }
}