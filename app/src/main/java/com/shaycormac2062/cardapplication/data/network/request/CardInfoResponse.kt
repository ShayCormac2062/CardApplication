package com.shaycormac2062.cardapplication.data.network.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardInfoResponse(
    @SerialName("bank")
    val bank: Bank? = null,
    @SerialName("brand")
    val brand: String? = null,
    @SerialName("country")
    val country: Country? = null,
    @SerialName("number")
    val number: Number? = null,
    @SerialName("prepaid")
    val prepaid: Boolean? = null,
    @SerialName("scheme")
    val scheme: String? = null,
    @SerialName("type")
    val type: String? = null
)
