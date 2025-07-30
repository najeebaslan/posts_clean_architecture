package com.najeeb.income_expense_tracker.features.posts.data.di

import android.content.Context
import androidx.room.Room
import com.najeeb.income_expense_tracker.core.Constants
import com.najeeb.income_expense_tracker.core.RetrofitFactory
import com.najeeb.income_expense_tracker.features.posts.data.api.PostsApiService
import com.najeeb.income_expense_tracker.features.posts.data.database.PostDAO
import com.najeeb.income_expense_tracker.features.posts.data.database.PostDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostDataModule {
  @Provides
  @Singleton
  fun provideApiService(): PostsApiService {
    return RetrofitFactory.createPostsRetrofitFactory(PostsApiService::class.java)
  }

  @Singleton
  @Provides
  fun provideRoomDataBase(@ApplicationContext context: Context): PostDataBase {
    return Room.databaseBuilder(
      context.applicationContext,
      PostDataBase::class.java,
      Constants.DATABASE_NAME
    ).fallbackToDestructiveMigration(true).build()
  }

  @Provides
  fun provideRoomDao(dataBase: PostDataBase): PostDAO {
    return dataBase.dao
  }

}