package com.shaycormac2062.cardapplication.di

import com.shaycormac2062.cardapplication.data.local.db.dao.CardDao
import com.shaycormac2062.cardapplication.data.network.api.BankApi
import com.shaycormac2062.cardapplication.data.repository.CardDatabaseRepositoryImpl
import com.shaycormac2062.cardapplication.data.repository.CardInfoRepositoryImpl
import com.shaycormac2062.cardapplication.domain.repository.CardDatabaseRepository
import com.shaycormac2062.cardapplication.domain.repository.CardInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun bindAilmentsRepository(
        api: BankApi
    ): CardInfoRepository =
        CardInfoRepositoryImpl(api)

    @Provides
    fun provideCardDatabaseRepository(
        dao: CardDao
    ): CardDatabaseRepository = CardDatabaseRepositoryImpl(dao)

}
