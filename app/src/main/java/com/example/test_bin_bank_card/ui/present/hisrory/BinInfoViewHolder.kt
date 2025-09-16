package com.example.test_bin_bank_card.ui.present.hisrory

import androidx.recyclerview.widget.RecyclerView
import com.example.test_bin_bank_card.databinding.ItemAdapterBinding
import com.example.test_bin_bank_card.domain.model.BinInfo

class BinInfoViewHolder(private val binding: ItemAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(binInfo: BinInfo) {
        binding.apply {
            binInfoText.text = binInfo.bin
            countryInfo.text = "${binInfo?.country?.name}\n${binInfo?.country?.currency}"
            bankInfo.text = "${binInfo?.bank?.name}" +
                    "\n${binInfo?.bank?.city}" +
                    "\n${binInfo?.bank?.phone}"
            typeCardInfo.text = "${binInfo?.scheme}/${binInfo?.type}"
        }
    }
}