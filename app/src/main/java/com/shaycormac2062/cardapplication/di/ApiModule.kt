package com.shaycormac2062.cardapplication.di

import com.shaycormac2062.cardapplication.data.network.api.BankApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideApi(
        @Named("retrofit") retrofit: Retrofit
    ): BankApi = retrofit.create(BankApi::class.java)

}
