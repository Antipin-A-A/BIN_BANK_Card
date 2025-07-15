package com.example.test_bin_bank_card.domain.api

import com.example.test_bin_bank_card.data.network.Resource
import com.example.test_bin_bank_card.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun searchBin(expression: String):Flow<Resource<BinInfo>>
    fun getHistoryBin(): Flow<List<BinInfo>>
}