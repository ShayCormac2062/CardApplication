package com.shaycormac2062.cardapplication.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_id")
    val id: Int,
    val name: String,
    @ColumnInfo(name = "is_right")
    val isRight: Boolean
)
