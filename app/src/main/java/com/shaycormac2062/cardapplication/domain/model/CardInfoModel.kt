package com.shaycormac2062.cardapplication.domain.model

data class CardInfoModel(
    val bank: BankModel?,
    val brand: String?,
    val country: CountryModel?,
    val number: NumberModel?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
)
