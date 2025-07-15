package com.example.test_bin_bank_card.data.bd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BinDao {
    @Insert(entity = BinInfoEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBin(track: BinInfoEntity)

    @Delete(entity = BinInfoEntity::class)
    suspend fun deleteBin(track: BinInfoEntity)

    @Query("SELECT * FROM bin_table")
    suspend fun getBin(): List<BinInfoEntity>

}