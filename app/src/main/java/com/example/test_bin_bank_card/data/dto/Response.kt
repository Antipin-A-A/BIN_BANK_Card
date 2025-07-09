package com.example.test_bin_bank_card.data.dto

import com.example.test_bin_bank_card.data.model.BinInfoDto

open class Response(var resultCode: Int = 0, var data: BinInfoDto? = null)