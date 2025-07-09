package com.example.test_bin_bank_card.data

import com.example.test_bin_bank_card.data.dto.Response
import com.example.test_bin_bank_card.data.dto.SearchRequest

interface NetworkClient {
    suspend fun doRequest(dto: SearchRequest): Response
}