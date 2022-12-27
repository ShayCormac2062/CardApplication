package com.shaycormac2062.cardapplication.domain.usecase

import com.shaycormac2062.cardapplication.domain.repository.CardDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class DeleteRequestHistoryUseCase @Inject constructor(
    private val repository: CardDatabaseRepository,
    @Named("default") private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke() {
        withContext(dispatcher) {
            repository.deleteRequestHistory()
        }
    }

}
