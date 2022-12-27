package com.shaycormac2062.cardapplication.data.repository

import com.shaycormac2062.cardapplication.data.network.api.BankApi
import com.shaycormac2062.cardapplication.data.network.mapper.CardMapper.toDomain
import com.shaycormac2062.cardapplication.domain.model.CardInfoModel
import com.shaycormac2062.cardapplication.domain.repository.CardInfoRepository
import com.shaycormac2062.cardapplication.utils.ApplicationException
import com.shaycormac2062.cardapplication.utils.RequestResult
import javax.inject.Inject

class CardInfoRepositoryImpl @Inject constructor(
    private val api: BankApi
) : CardInfoRepository {

    override suspend fun getCardInfo(number: String): RequestResult<CardInfoModel?> = try {
        val result = api.getNumber(number)
        with(result) {
            if (code() == 404) {
                RequestResult.Error(
                    exception = ApplicationException.NotFoundCardNumberException()
                )
            } else if (isSuccessful && body() != null) {
                RequestResult.Success(
                    data = body()?.toDomain()
                )
            } else RequestResult.Error(
                exception = ApplicationException.ApiException()
            )
        }
    } catch (ex: Throwable) {
        RequestResult.Error(
            exception = ApplicationException.InternetException()
        )
    }

}
