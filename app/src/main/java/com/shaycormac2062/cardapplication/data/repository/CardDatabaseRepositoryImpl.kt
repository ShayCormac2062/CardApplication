package com.shaycormac2062.cardapplication.data.repository

import com.shaycormac2062.cardapplication.data.local.db.dao.CardDao
import com.shaycormac2062.cardapplication.data.local.db.entity.CardEntity
import com.shaycormac2062.cardapplication.data.network.mapper.CardMapper.toModel
import com.shaycormac2062.cardapplication.domain.model.DatabaseModel
import com.shaycormac2062.cardapplication.domain.repository.CardDatabaseRepository
import javax.inject.Inject

class CardDatabaseRepositoryImpl @Inject constructor(
    private val dao: CardDao
) : CardDatabaseRepository {

    override suspend fun add(entity: CardEntity) {
        dao.add(entity)
    }

    override suspend fun getRequestHistory(): List<DatabaseModel?> {
        return dao.getRequestHistory().map {
            it?.toModel()
        }.reversed()
    }

    override suspend fun deleteRequestHistory() {
        dao.cleanHistory()
    }
}
