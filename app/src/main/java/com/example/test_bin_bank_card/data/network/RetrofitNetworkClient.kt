package com.example.test_bin_bank_card.data.network

import android.util.Log
import com.example.test_bin_bank_card.data.NetworkClient
import com.example.test_bin_bank_card.data.dto.Response
import com.example.test_bin_bank_card.data.dto.SearchRequest
import com.example.test_bin_bank_card.utilit.Object.CONNECT_OK
import com.example.test_bin_bank_card.utilit.Object.ERROR_CONNECT
import com.example.test_bin_bank_card.utilit.Object.ERROR_FILE_NOT_FOUND
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.apply

class RetrofitNetworkClient(
    private val retrofit: RetrofitManager, private val connectedManager: ConnectedManager
) : NetworkClient {
    override suspend fun doRequest(dto: SearchRequest): Response {
        if (!connectedManager.isConnected()) {
            return Response().apply { resultCode = ERROR_CONNECT }
        }
        return withContext(Dispatchers.IO) {
            try {
                val binInfo = retrofit.binApi.getBinInfo(bin = dto.expression)
                Response().apply {
                    resultCode = CONNECT_OK
                    data = binInfo
                }
            } catch (e: Throwable) {
                Log.e("Log2", "Network error", e)
                Response().apply { resultCode = ERROR_FILE_NOT_FOUND }
            }
        }
    }
}



