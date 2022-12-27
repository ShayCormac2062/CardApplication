package com.shaycormac2062.cardapplication.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaycormac2062.cardapplication.data.local.db.entity.CardEntity

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(name: CardEntity)

    @Query("SELECT * FROM cards")
    suspend fun getRequestHistory(): List<CardEntity?>

    @Query("DELETE FROM cards")
    suspend fun cleanHistory()
}
