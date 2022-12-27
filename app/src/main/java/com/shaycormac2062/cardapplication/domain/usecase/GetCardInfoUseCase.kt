package com.shaycormac2062.cardapplication.domain.usecase

import com.shaycormac2062.cardapplication.domain.repository.CardInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetCardInfoUseCase @Inject constructor(
    private val repository: CardInfoRepository,
    @Named("io") private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(number: String) =
        withContext(dispatcher) {
            repository.getCardInfo(number)
        }

}
