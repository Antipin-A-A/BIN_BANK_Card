package com.example.test_bin_bank_card.data.network

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinlistApi {
    @GET("{bin}")
   suspend fun getBinInfo(@Path("bin") bin: String): Response
}

