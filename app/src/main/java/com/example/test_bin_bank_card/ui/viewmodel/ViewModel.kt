package com.example.test_bin_bank_card.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_bin_bank_card.domain.api.Interact
import com.example.test_bin_bank_card.domain.model.BinInfo
import com.example.test_bin_bank_card.ui.present.search.UiState
import com.example.test_bin_bank_card.utilit.Object
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewModel(
    private val interactor: Interact
) : ViewModel() {

    private val _searchState = MutableStateFlow<UiState>(UiState.Loading)
 //   val searchState: StateFlow<UiState> = _searchState

    var latestSearchText: String? = null
    private var searchJob: Job? = null

    val mediatorStateFlow: StateFlow<UiState> = _searchState
        .map { uiState ->
            when (uiState) {
                is UiState.Loading -> uiState
                is UiState.Content -> uiState
                is UiState.Error -> uiState
            }
        }
        .stateIn(
            scope = CoroutineScope(Dispatchers.Main + SupervisorJob()),
            started = SharingStarted.Companion.Lazily,
            initialValue = UiState.Loading
        )

    fun observeMediaState(): StateFlow<UiState> = mediatorStateFlow

    fun searchBin(searchText: String) {
        if (searchText.isNotEmpty()) {
            renderState(UiState.Loading)
            viewModelScope.launch {
                interactor
                    .searchBin(searchText)
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            }
        }
    }

    private fun processResult(foundTreks: BinInfo?, errorMessage: String?) {
//        val binSearch = mutableListOf<BinInfo>()
//        if (foundTreks != null) {
//            binSearch.addAll(foundTreks)
//        }
        when {
            errorMessage != null -> {
                if (errorMessage == "${Object.ERROR_CONNECT}") {
                    renderState(UiState.Error(errorMessage))
                }

            }

//            binSearch.isEmpty() -> {
//                renderState(UiState.Empty)
//            }

            else -> {
                renderState(UiState.Content(foundTreks))
                Log.i(
                    "Log1",
                    " ${foundTreks?.brand}, ${foundTreks?.prepaid}, ${foundTreks?.type}, ${foundTreks?.bank}, ${foundTreks?.country}${foundTreks?.scheme}${foundTreks?.number}"
                )
            }
        }
    }

    private fun renderState(state: UiState) {
        _searchState.value = state
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }
        latestSearchText = changedText
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            searchBin(changedText)
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 1000L
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

}