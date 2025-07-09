package com.example.test_bin_bank_card.ui.present.search

import com.example.test_bin_bank_card.domain.model.BinInfo

sealed interface UiState {
    data object Loading : UiState
    data class Content(val binInfo: BinInfo?) : UiState
    data class Error(val errorMessage: String) : UiState
}