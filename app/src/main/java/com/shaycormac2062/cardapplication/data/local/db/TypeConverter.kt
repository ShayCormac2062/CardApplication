package com.shaycormac2062.cardapplication.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.shaycormac2062.cardapplication.data.local.db.entity.CardEntity

class TypeConverter {

    @TypeConverter
    fun fromString(value: String) =
        Gson().fromJson(
            value,
            Array<CardEntity>::class.java
        ).toList()

    @TypeConverter
    fun fromList(list: List<CardEntity>?) =
        Gson().toJson(list) ?: ""
}
