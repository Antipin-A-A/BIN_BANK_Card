package com.example.test_bin_bank_card.ui.present.hisrory

import androidx.recyclerview.widget.RecyclerView
import com.example.test_bin_bank_card.databinding.ItemAdapterBinding
import com.example.test_bin_bank_card.domain.model.BinInfo

class BinInfoViewHolder( private val binding: ItemAdapterBinding,) : RecyclerView.ViewHolder(binding.root) {

    fun bind(binInfo: BinInfo) {
        val value = binInfo.scheme
        binding.apply {
            countryInfo.text ="${binInfo?.country?.name}, ${binInfo?.country?.currency}\n${binInfo?.country?.latitude}\n${binInfo?.country?.longitude}"
            bankName.text ="${binInfo?.bank?.name}" +
                    "\n city - ${binInfo?.bank?.city}" +
                    "\n number -${binInfo?.bank?.phone}"
            bankType.text = "${binInfo?.scheme}/${binInfo?.type}"
            countryName.text = binInfo?.country?.name
        }
    }
}