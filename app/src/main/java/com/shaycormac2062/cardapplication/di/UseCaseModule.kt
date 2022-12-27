package com.shaycormac2062.cardapplication.di

import com.shaycormac2062.cardapplication.domain.repository.CardDatabaseRepository
import com.shaycormac2062.cardapplication.domain.repository.CardInfoRepository
import com.shaycormac2062.cardapplication.domain.usecase.AddCardNumberUseCase
import com.shaycormac2062.cardapplication.domain.usecase.DeleteRequestHistoryUseCase
import com.shaycormac2062.cardapplication.domain.usecase.GetCardInfoUseCase
import com.shaycormac2062.cardapplication.domain.usecase.GetRequestHistoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun bindGetCardInfoUseCase(
        repository: CardInfoRepository,
        @Named("io") dispatcher: CoroutineDispatcher
    ): GetCardInfoUseCase = GetCardInfoUseCase(
        repository = repository,
        dispatcher = dispatcher
    )

    @Provides
    fun bindGetRequestHistoryUseCase(
        repository: CardDatabaseRepository,
        @Named("default") dispatcher: CoroutineDispatcher
    ): GetRequestHistoryUseCase = GetRequestHistoryUseCase(
        repository = repository,
        dispatcher = dispatcher
    )

    @Provides
    fun bindDeleteRequestHistoryUseCase(
        repository: CardDatabaseRepository,
        @Named("default") dispatcher: CoroutineDispatcher
    ): DeleteRequestHistoryUseCase = DeleteRequestHistoryUseCase(
        repository = repository,
        dispatcher = dispatcher
    )

    @Provides
    fun bindAddCardNumberUseCase(
        repository: CardDatabaseRepository,
        @Named("default") dispatcher: CoroutineDispatcher
    ): AddCardNumberUseCase = AddCardNumberUseCase(
        repository = repository,
        dispatcher = dispatcher
    )

}
