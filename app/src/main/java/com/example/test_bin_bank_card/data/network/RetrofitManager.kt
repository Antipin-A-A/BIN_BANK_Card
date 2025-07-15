package com.example.test_bin_bank_card.data.network

import android.R.attr.level
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class RetrofitManager {

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val binURL = "https://lookup.binlist.net"

    private val retrofit = Retrofit.Builder()
        .baseUrl(binURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val binApi: BinApi = retrofit.create(BinApi::class.java)
}