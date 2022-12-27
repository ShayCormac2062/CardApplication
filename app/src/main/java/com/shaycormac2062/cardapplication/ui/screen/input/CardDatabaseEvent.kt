package com.shaycormac2062.cardapplication.ui.screen.input

import com.shaycormac2062.cardapplication.domain.model.DatabaseModel

sealed class CardDatabaseEvent {

    object CardDatabaseEmptyEvent : CardDatabaseEvent()

    object CardDatabaseLoadingEvent : CardDatabaseEvent()

    data class CardDatabaseSuccessEvent(
        val cardInfo: List<DatabaseModel?>
    ) : CardDatabaseEvent()

}
