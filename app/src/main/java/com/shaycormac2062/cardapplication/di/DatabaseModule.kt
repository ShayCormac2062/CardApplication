package com.shaycormac2062.cardapplication.di

import android.content.Context
import androidx.room.Room
import com.shaycormac2062.cardapplication.data.local.AppDatabase
import com.shaycormac2062.cardapplication.data.local.db.dao.CardDao
import com.shaycormac2062.cardapplication.data.repository.CardDatabaseRepositoryImpl
import com.shaycormac2062.cardapplication.domain.repository.CardDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideCityDao(
        db: AppDatabase
    ): CardDao = db.cardDao()

    @Singleton
    @Provides
    fun provideDb(
        @ApplicationContext context: Context
    ): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .build()

    companion object {
        private const val DATABASE_NAME = "cards"
    }

}
