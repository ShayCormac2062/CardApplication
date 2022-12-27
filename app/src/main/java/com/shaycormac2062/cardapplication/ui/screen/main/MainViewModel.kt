package com.shaycormac2062.cardapplication.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaycormac2062.cardapplication.domain.usecase.AddCardNumberUseCase
import com.shaycormac2062.cardapplication.domain.usecase.GetCardInfoUseCase
import com.shaycormac2062.cardapplication.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCardInfoUseCase: GetCardInfoUseCase,
    private val addCardNumberUseCase: AddCardNumberUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CardInfoEvent> =
        MutableStateFlow(CardInfoEvent.CardInfoEmptyEvent)
    val uiState: StateFlow<CardInfoEvent> = _uiState

    fun getCardInfo(number: String) {
        _uiState.value = CardInfoEvent.CardInfoLoadingEvent
        viewModelScope.launch {
            val result = getCardInfoUseCase(number)
            _uiState.value = when (result) {
                is RequestResult.Success -> {
                    addCardNumberUseCase(
                        number = number,
                        isRight = true
                    )
                    CardInfoEvent.CardInfoSuccessEvent(result.data)
                }
                else -> {
                    addCardNumberUseCase(
                        number = number,
                        isRight = false
                    )
                    CardInfoEvent.CardInfoErrorEvent(
                        errorNumber = number,
                        exception = result.exception
                    )
                }
            }
        }
    }

    fun resetData() {
        _uiState.value = CardInfoEvent.CardInfoEmptyEvent
    }

}
