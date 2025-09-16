package com.example.test_bin_bank_card.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_bin_bank_card.domain.api.Interact
import com.example.test_bin_bank_card.domain.model.BinInfo
import com.example.test_bin_bank_card.ui.present.search.UiState
import com.example.test_bin_bank_card.utilit.Object.ERROR_CONNECT
import com.example.test_bin_bank_card.utilit.Object.SERVER_ERROR_LIMIT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FragmentViewModel(
    private val interactor: Interact
) : ViewModel() {

    private val _searchState = MutableStateFlow<UiState>(UiState.Empty)
    //   val searchState: StateFlow<UiState> = _searchState

    var latestSearchText: String? = null
    private var searchJob: Job? = null


    init {
        getHistory()
    }

    val mediatorStateFlow: StateFlow<UiState> = _searchState.map { uiState ->
            when (uiState) {
                is UiState.Loading -> uiState
                is UiState.Content -> uiState
                is UiState.Error -> uiState
                is UiState.Empty -> uiState
            }
        }.stateIn(
            scope = CoroutineScope(Dispatchers.Main + SupervisorJob()),
            started = SharingStarted.Companion.Lazily,
            initialValue = UiState.Empty
        )

    fun observeMediaState(): StateFlow<UiState> = mediatorStateFlow

    fun searchBin(searchText: String) {
        if (searchText.isNotEmpty()) {
            renderState(UiState.Loading)
            viewModelScope.launch {
                interactor.searchBin(searchText).collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            }
        }
    }

    private fun processResult(foundBinInfo: BinInfo?, errorMessage: String?) {
        when {
            errorMessage != null -> {
                if (errorMessage == "$ERROR_CONNECT") {
                    Log.i("LogError -1 ", errorMessage)
                    renderState(UiState.Error(errorMessage))
                }else if  (errorMessage == "$SERVER_ERROR_LIMIT"){
                    Log.i("LogError -2", errorMessage)
                    renderState(UiState.Error(errorMessage))
                } else {
                    Log.i("LogError - 3", errorMessage)
                    renderState(UiState.Error(errorMessage))
                }
            }

            else -> {
                renderState(UiState.Content(foundBinInfo))
                Log.i(
                    "Log1",
                    " ${foundBinInfo?.brand}, ${foundBinInfo?.prepaid}, ${foundBinInfo?.type}, ${foundBinInfo?.bank}, ${foundBinInfo?.country}${foundBinInfo?.scheme}${foundBinInfo?.number}"
                )
            }
        }
    }

    fun getHistory(): Flow<List<BinInfo>> {
        val history = interactor.getHistoryBin()
        Log.i("LogHistory", " ${history.map { it -> it.size }} ")
        return history
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