package com.example.test_bin_bank_card.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class RetrofitManager {
    private val iTunesBaseURL = "https://lookup.binlist.net"

    private val retrofit = Retrofit.Builder()
        .baseUrl(iTunesBaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val iTunesService: BinlistApi = retrofit.create(BinlistApi::class.java)
}