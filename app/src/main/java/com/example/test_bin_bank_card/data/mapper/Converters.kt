package com.example.test_bin_bank_card.data.mapper

import androidx.room.TypeConverter
import com.example.test_bin_bank_card.domain.model.BankInfo
import com.example.test_bin_bank_card.domain.model.CountryInfo
import com.example.test_bin_bank_card.domain.model.NumberInfo
import com.google.gson.Gson
import kotlin.collections.joinToString
import kotlin.collections.map
import kotlin.text.split
import kotlin.text.toInt

class Converters {
    @TypeConverter
    fun fromNumberInfo(numberInfo: NumberInfo?): String? {
        return Gson().toJson(numberInfo)
    }

    @TypeConverter
    fun toNumberInfo(data: String?): NumberInfo? {
        return data?.let { Gson().fromJson(it, NumberInfo::class.java) }
    }
    @TypeConverter
    fun fromCountryInfo(countryInfo: CountryInfo?): String? = Gson().toJson(countryInfo)

    @TypeConverter
    fun toCountryInfo(data: String?): CountryInfo? =
        data?.let { Gson().fromJson(it, CountryInfo::class.java) }

    @TypeConverter
    fun fromBankInfo(bankInfo: BankInfo?): String? = Gson().toJson(bankInfo)

    @TypeConverter
    fun toBankInfo(data: String?): BankInfo? =
        data?.let { Gson().fromJson(it, BankInfo::class.java) }
}