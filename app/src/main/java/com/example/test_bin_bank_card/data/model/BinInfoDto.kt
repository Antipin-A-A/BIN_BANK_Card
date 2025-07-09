package com.example.test_bin_bank_card.data.model

import com.example.test_bin_bank_card.domain.model.BankInfo
import com.example.test_bin_bank_card.domain.model.CountryInfo
import com.example.test_bin_bank_card.domain.model.NumberInfo

data class BinInfoDto(
    val number: NumberInfo?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryInfo?,
    val bank: BankInfo?
)