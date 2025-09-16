package com.example.test_bin_bank_card.data.mapper

import com.example.test_bin_bank_card.domain.model.BankInfo
import com.example.test_bin_bank_card.domain.model.CountryInfo

object ConvectorNull {
    fun cleanCountryInfo(country: CountryInfo?): CountryInfo {
        if (country == null) {
            return CountryInfo("-", "-", "-", "-", "-", 0, 0)
        }

        fun cleanString(value: String?) = if (value == "null" || value == null) "-" else value

        return CountryInfo(
            numeric = cleanString(country.numeric),
            alpha2 = cleanString(country.alpha2),
            name = cleanString(country.name),
            emoji = cleanString(country.emoji),
            currency = cleanString(country.currency),
            latitude = country.latitude ?: 0,
            longitude = country.longitude ?: 0
        )
    }

    fun cleanBankInfo(country: BankInfo?): BankInfo {
        if (country == null) {
            return BankInfo("-", "-", "-", "-")
        }

        fun cleanString(value: String?) = if (value == "null" || value == null) "-" else value

        return BankInfo(
            name = cleanString(country.name),
            url = cleanString(country.url),
            phone = cleanString(country.phone),
            city = cleanString(country.city)
        )
    }
}