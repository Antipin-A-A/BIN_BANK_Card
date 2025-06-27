package com.example.test_bin_bank_card.data

import com.example.playlistmaker.search.data.dto.Response

interface NetworkClient {
   suspend fun doRequest(dto: Any): Response
}