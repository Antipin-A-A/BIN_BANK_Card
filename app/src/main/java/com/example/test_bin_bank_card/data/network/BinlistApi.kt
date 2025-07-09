package com.example.test_bin_bank_card.data.network

import com.example.test_bin_bank_card.data.model.BinInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinInfoDto
}