package com.example.test_bin_bank_card.domain.api

import com.example.test_bin_bank_card.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

interface Interact {
    fun searchBin(expression: String): Flow<Pair<BinInfo?, String?>>
    fun getHistoryBin(): Flow<List<BinInfo>>
}