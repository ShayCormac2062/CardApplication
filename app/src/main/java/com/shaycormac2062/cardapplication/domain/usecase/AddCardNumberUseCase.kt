package com.shaycormac2062.cardapplication.domain.usecase

import com.shaycormac2062.cardapplication.data.local.db.entity.CardEntity
import com.shaycormac2062.cardapplication.domain.repository.CardDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class AddCardNumberUseCase @Inject constructor(
    private val repository: CardDatabaseRepository,
    @Named("default") private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(
        number: String,
        isRight: Boolean
    ) {
        withContext(dispatcher) {
            repository.add(
                CardEntity(
                    id = 0,
                    name = number,
                    isRight = isRight
                )
            )
        }
    }

}
