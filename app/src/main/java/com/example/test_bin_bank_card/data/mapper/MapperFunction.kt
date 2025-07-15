package com.example.test_bin_bank_card.data.mapper

import com.example.test_bin_bank_card.data.bd.BinInfoEntity
import com.example.test_bin_bank_card.data.model.BinInfoDto
import com.example.test_bin_bank_card.domain.model.BankInfo
import com.example.test_bin_bank_card.domain.model.BinInfo
import com.example.test_bin_bank_card.domain.model.CountryInfo
import com.example.test_bin_bank_card.domain.model.NumberInfo

fun BinInfoDto.toDomainModel(): BinInfo {
    return BinInfo(
        number = this.number ?: NumberInfo(0, false), // дефолтный объект или null-safe значение
        scheme = this.scheme ?: "unknown",
        type = this.type ?: "unknown",
        bank = this.bank ?: BankInfo("", "", "", ""),
        prepaid = this.prepaid ?: false,
        country = this.country ?: CountryInfo("", "", "", "", "", 0, 0),
        brand = this.brand ?: "unknown"
    )
}

fun BinInfoEntity.toDataModel(): BinInfo {
    return BinInfo(
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
        number = this.number ?: NumberInfo(0, false), // дефолтный объект или null-safe значение
        scheme = this.scheme ?: "unknown",
        type = this.type ?: "unknown",
        bank = this.bank ?: BankInfo("", "", "", ""),
        prepaid = this.prepaid ?: false,
        country = this.country ?: CountryInfo("", "", "", "", "", 0, 0),
        brand = this.brand ?: "unknown"
    )
}