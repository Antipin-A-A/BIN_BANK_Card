package com.example.test_bin_bank_card.domain.imp

import com.example.test_bin_bank_card.data.network.Resource
import com.example.test_bin_bank_card.domain.api.Interact
import com.example.test_bin_bank_card.domain.api.Repository
import com.example.test_bin_bank_card.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InteractImp(private val repository: Repository) : Interact {
    override fun searchBin(expression: String): Flow<Pair<BinInfo?, String?>> {
        return repository.searchBin(expression).map { result ->
            when (result) {
                is Resource.Success<*> -> {
                    Pair(result.data, null)
                }

                is Resource.Error<*> -> {
                    Pair(null, result.message)
                }
            }
        }
    }

    override fun getHistoryBin(): Flow<List<BinInfo>> {
        return repository.getHistoryBin()
    }
}