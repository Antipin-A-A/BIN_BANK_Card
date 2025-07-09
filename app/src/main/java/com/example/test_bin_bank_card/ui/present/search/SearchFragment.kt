package com.example.test_bin_bank_card.ui.present.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.test_bin_bank_card.databinding.FragmentSearchBinding
import com.example.test_bin_bank_card.domain.model.BinInfo
import com.example.test_bin_bank_card.ui.present.search.UiState
import com.example.test_bin_bank_card.ui.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = requireNotNull(_binding)

    //  private var adapter: VacancyAdapter? = null
    private val viewModel by viewModel<ViewModel>()
    private var isClickAllowed = true
    private var filterMenuItem: MenuItem? = null
    private var isToastAllowed = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
                Log.i("LogFrag", "Error")
            }

            is UiState.Loading -> {
                Log.i("LogFrag", "Loading")
            }
        }
    }

    private fun showContent(binInfo: BinInfo?) = with(binding) {
      //  bankName.text = "${binInfo?.bank?.name}"
        countryInfo.text ="${binInfo?.country?.name}, ${binInfo?.country?.currency}\n${binInfo?.country?.latitude}\n${binInfo?.country?.longitude}"
        bankName.text ="${binInfo?.bank?.name}" +
                "\n city - ${binInfo?.bank?.city}" +
                "\n number -${binInfo?.bank?.phone}"
        bankType.text = "${binInfo?.scheme}/${binInfo?.type}"
        countryName.text = binInfo?.country?.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}