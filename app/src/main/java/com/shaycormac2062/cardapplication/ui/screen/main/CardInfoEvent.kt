package com.shaycormac2062.cardapplication.ui.screen.main

import com.shaycormac2062.cardapplication.domain.model.CardInfoModel
import com.shaycormac2062.cardapplication.utils.ApplicationException

sealed class CardInfoEvent {

    object CardInfoEmptyEvent : CardInfoEvent()

    object CardInfoLoadingEvent : CardInfoEvent()

    data class CardInfoErrorEvent(
        val errorNumber: String?,
        val exception: ApplicationException?
    ) : CardInfoEvent()

    data class CardInfoSuccessEvent(
        val cardInfo: CardInfoModel?
    ) : CardInfoEvent()

}
