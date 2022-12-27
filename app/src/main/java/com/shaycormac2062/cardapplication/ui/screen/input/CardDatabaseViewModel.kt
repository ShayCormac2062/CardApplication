package com.shaycormac2062.cardapplication.ui.screen.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaycormac2062.cardapplication.domain.usecase.DeleteRequestHistoryUseCase
import com.shaycormac2062.cardapplication.domain.usecase.GetRequestHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDatabaseViewModel @Inject constructor(
    private val getRequestHistoryUseCase: GetRequestHistoryUseCase,
    private val deleteRequestHistoryUseCase: DeleteRequestHistoryUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CardDatabaseEvent> =
        MutableStateFlow(CardDatabaseEvent.CardDatabaseEmptyEvent)
    val uiState: StateFlow<CardDatabaseEvent> = _uiState

    fun getRequestHistory() {
        _uiState.value = CardDatabaseEvent.CardDatabaseLoadingEvent
        viewModelScope.launch {
            val result = getRequestHistoryUseCase()
            _uiState.value = if (result.isNotEmpty()) {
                CardDatabaseEvent.CardDatabaseSuccessEvent(result)
            } else CardDatabaseEvent.CardDatabaseEmptyEvent
        }
    }

    fun resetData() {
        viewModelScope.launch {
            deleteRequestHistoryUseCase()
            _uiState.value = CardDatabaseEvent.CardDatabaseEmptyEvent
        }
    }
}
