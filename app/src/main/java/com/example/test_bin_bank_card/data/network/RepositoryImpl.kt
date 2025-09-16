package com.example.test_bin_bank_card.data.network

import android.util.Log
import com.example.test_bin_bank_card.data.NetworkClient
import com.example.test_bin_bank_card.data.bd.AppDataBase
import com.example.test_bin_bank_card.data.dto.SearchRequest
import com.example.test_bin_bank_card.data.mapper.toDataModel
import com.example.test_bin_bank_card.data.mapper.toDomainModel
import com.example.test_bin_bank_card.data.mapper.toDomainModelEntity
import com.example.test_bin_bank_card.data.model.BinInfoDto
import com.example.test_bin_bank_card.domain.api.Repository
import com.example.test_bin_bank_card.domain.model.BinInfo
import com.example.test_bin_bank_card.utilit.Object.CONNECT_OK
import com.example.test_bin_bank_card.utilit.Object.ERROR_CONNECT
import com.example.test_bin_bank_card.utilit.Object.ERROR_FILE_NOT_FOUND
import com.example.test_bin_bank_card.utilit.Object.SERVER_ERROR
import com.example.test_bin_bank_card.utilit.Object.SERVER_ERROR_LIMIT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val networkClient: NetworkClient,
    private val appDataBase: AppDataBase,
) : Repository {
    override fun searchBin(expression: String): Flow<Resource<BinInfo>> = flow {
        val response = networkClient.doRequest(SearchRequest(expression))
        when (response.resultCode) {
            ERROR_CONNECT -> {
                emit(Resource.Error("$ERROR_CONNECT"))
            }

            CONNECT_OK -> {
                val dto: BinInfoDto? = response.data
                if (dto != null) {
                    appDataBase.binDao().insertBin(dto.toDomainModelEntity(expression))
                    val domainModel = Resource.Success(dto.toDomainModel())
                    emit(domainModel)
                } else {
                    Log.i("LogReposit1", "null")
                    emit(Resource.Error("An empty response from the server"))
                }
            }

            SERVER_ERROR_LIMIT -> {
                emit(Resource.Error("Request limit exceeded, please try again later"))
            }
            SERVER_ERROR -> {
                emit(Resource.Error(" Server error: incorrect data"))
            }
            else -> {
                Log.i("LogReposit-3", "${response.resultCode}")
                emit(Resource.Error("$ERROR_FILE_NOT_FOUND"))
            }
        }
    }

    override fun getHistoryBin(): Flow<List<BinInfo>> = flow {
        val binInfoHistory = appDataBase.binDao().getBin()
        emit(binInfoHistory.map { it.toDataModel() })
    }
}


