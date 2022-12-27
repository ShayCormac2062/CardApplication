package com.shaycormac2062.cardapplication.domain.repository

import com.shaycormac2062.cardapplication.domain.model.CardInfoModel
import com.shaycormac2062.cardapplication.utils.RequestResult

interface CardInfoRepository {

    suspend fun getCardInfo(number: String) : RequestResult<CardInfoModel?>

}
