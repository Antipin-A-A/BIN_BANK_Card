package com.example.test_bin_bank_card.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test_bin_bank_card.data.mapper.Converters

@Database(
    version = 2,
    entities = [BinInfoEntity::class]
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun binDao(): BinDao
}