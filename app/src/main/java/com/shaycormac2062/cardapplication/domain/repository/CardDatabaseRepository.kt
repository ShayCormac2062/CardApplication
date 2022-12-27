package com.shaycormac2062.cardapplication.domain.repository

import com.shaycormac2062.cardapplication.data.local.db.entity.CardEntity
import com.shaycormac2062.cardapplication.domain.model.DatabaseModel

interface CardDatabaseRepository {

    suspend fun add(entity: CardEntity)
    suspend fun getRequestHistory(): List<DatabaseModel?>
    suspend fun deleteRequestHistory()

}
