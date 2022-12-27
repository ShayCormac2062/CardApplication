package com.shaycormac2062.cardapplication.data.network.api

import com.shaycormac2062.cardapplication.data.network.request.CardInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BankApi {

    @GET("{number}")
    suspend fun getNumber(
        @Path("number") number: String
    ): Response<CardInfoResponse?>

}
