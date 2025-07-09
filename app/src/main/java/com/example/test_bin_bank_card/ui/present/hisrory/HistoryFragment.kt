package com.example.test_bin_bank_card.ui.present.hisrory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test_bin_bank_card.databinding.FragmentHistoryBinding
import com.example.test_bin_bank_card.ui.viewmodel.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding: FragmentHistoryBinding get() = requireNotNull(_binding)
    private val viewModel by viewModel<ViewModel>()

    //  private var adapter: VacancyAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}