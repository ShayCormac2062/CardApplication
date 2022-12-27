package com.shaycormac2062.cardapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shaycormac2062.cardapplication.data.local.db.TypeConverter
import com.shaycormac2062.cardapplication.data.local.db.dao.CardDao
import com.shaycormac2062.cardapplication.data.local.db.entity.CardEntity

@Database(
    entities = [CardEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}
