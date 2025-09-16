package com.example.test_bin_bank_card.data.mapper

import com.example.test_bin_bank_card.data.bd.BinInfoEntity
import com.example.test_bin_bank_card.data.model.BinInfoDto
import com.example.test_bin_bank_card.domain.model.BankInfo
import com.example.test_bin_bank_card.domain.model.BinInfo
import com.example.test_bin_bank_card.domain.model.CountryInfo
import com.example.test_bin_bank_card.domain.model.NumberInfo

fun BinInfoDto.toDomainModel(): BinInfo {
    return BinInfo(
        bin = this.bin,
        number = this.number ?: NumberInfo(0, false),
        scheme = this.scheme ?: "-",
        type = this.type ?: "-",
        bank = ConvectorNull.cleanBankInfo(this.bank),
        prepaid = this.prepaid == false,
        country = ConvectorNull.cleanCountryInfo(this.country),
        brand = this.brand ?: "-"
    )
}

fun BinInfoEntity.toDataModel(): BinInfo {
    return BinInfo(
        bin = this.bin,
        number = this.number,
        scheme = this.scheme,
        type = this.type,
        bank = this.bank,
        prepaid = this.prepaid,
        country = this.country,
        brand = this.brand
    )
}

fun BinInfoDto.toDomainModelEntity(expression: String): BinInfoEntity {
    return BinInfoEntity(
        bin = expression,
        number = this.number ?: NumberInfo(0, false),
        scheme = this.scheme ?: "-",
        type = this.type ?: "-",
        bank = ConvectorNull.cleanBankInfo(this.bank),
        prepaid = this.prepaid ?: false,
        country = ConvectorNull.cleanCountryInfo(this.country),
        brand = this.brand ?: "-"
    )
}