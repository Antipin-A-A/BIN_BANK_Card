package com.example.test_bin_bank_card.data.bd

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_bin_bank_card.domain.model.BankInfo
import com.example.test_bin_bank_card.domain.model.CountryInfo
import com.example.test_bin_bank_card.domain.model.NumberInfo

@Entity(tableName = "bin_table")
data class BinInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val bin: String,
    val number: NumberInfo?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryInfo?,
    val bank: BankInfo?
)