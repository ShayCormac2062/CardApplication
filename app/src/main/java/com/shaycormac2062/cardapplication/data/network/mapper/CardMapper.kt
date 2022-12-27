package com.shaycormac2062.cardapplication.data.network.mapper

import com.shaycormac2062.cardapplication.data.local.db.entity.CardEntity
import com.shaycormac2062.cardapplication.data.network.request.Bank
import com.shaycormac2062.cardapplication.data.network.request.CardInfoResponse
import com.shaycormac2062.cardapplication.data.network.request.Country
import com.shaycormac2062.cardapplication.data.network.request.Number
import com.shaycormac2062.cardapplication.domain.model.*

object CardMapper {

    fun CardInfoResponse.toDomain(): CardInfoModel = CardInfoModel(
        bank = bank?.toDomain(),
        brand = brand ?: "unknown",
        country = country?.toDomain(),
        number = number?.toDomain(),
        prepaid = prepaid ?: false,
        scheme = scheme ?: "unknown",
        type = type ?: "unknown"
    )

    private fun Number.toDomain(): NumberModel = NumberModel(
        length = length ?: 0,
        luhn = luhn ?: false
    )

    private fun Country.toDomain(): CountryModel = CountryModel(
        alpha2 = alpha2 ?: "unknown",
        currency = currency ?: "unknown",
        emoji = emoji ?: "unknown",
        latitude = latitude ?: 0,
        longitude = longitude ?: 0,
        name = name ?: "unknown",
        numeric = numeric ?: "unknown"
    )

    private fun Bank.toDomain(): BankModel = BankModel(
        city = city ?: "unknown",
        name = name ?: "unknown",
        phone = phone ?: "unknown",
        url = url ?: "unknown"
    )

    fun CardEntity.toModel() = DatabaseModel(
        name = name,
        isRight = isRight
    )


}
