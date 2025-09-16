package com.example.test_bin_bank_card.ui.present.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.test_bin_bank_card.databinding.FragmentSearchBinding
import com.example.test_bin_bank_card.domain.model.BinInfo
import com.example.test_bin_bank_card.ui.viewmodel.FragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = requireNotNull(_binding)
    private val viewModel by viewModel<FragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.observeMediaState().collect { uiState ->
                render(uiState)
            }
        }

        binding.buttonSearch.setOnClickListener {
            viewModel.searchDebounce(binding.searchView.text.toString())
        }
    }

    private fun render(state: UiState) {
        when (state) {
            is UiState.Content -> {
                showContent(state.binInfo)
                Log.i("LogFrag", "Content")
            }
            is UiState.Error -> {
                showMessage(state.errorMessage, "", 5)
                Log.i("LogFrag", "Error")
            }
            is UiState.Loading -> {
                load()
                Log.i("LogFrag", "Loading")
            }
            is UiState.Empty -> {
                Log.i("LogFrag", "Enpty")
            }
        }
    }

    private fun showContent(binInfo: BinInfo?) = with(binding) {
        bottomProgressBar.isVisible = false
        linearLayout.isVisible = false
        countryInfo.text =
            "${binInfo?.country?.name}, \n${binInfo?.country?.currency}"
        bankInfo.text =
            "${binInfo?.bank?.name}" + "\n${binInfo?.bank?.url}" +"\n${binInfo?.bank?.city}" + "\n${binInfo?.bank?.phone}"
        typeCardInfo.text = "${binInfo?.scheme}/${binInfo?.type}"
    }
    private fun load() = with(binding) {
        linearLayout.isVisible = true
        bottomProgressBar.isVisible = true
    }

    private fun showMessage(text: String, additionalMessage: String, drawable: Int) =
        with(binding) {
            linearLayout.isVisible = false
            bottomProgressBar.isVisible = false

            Snackbar.make(root, text, Snackbar.LENGTH_LONG).show()
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}