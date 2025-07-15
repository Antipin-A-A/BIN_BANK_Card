package ru.practicum.android.diploma.ui.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_bin_bank_card.databinding.ItemAdapterBinding
import com.example.test_bin_bank_card.domain.model.BinInfo
import com.example.test_bin_bank_card.ui.present.hisrory.BinInfoViewHolder


class BinInfoAdapter(var biInfo: List<BinInfo> = emptyList()) : RecyclerView.Adapter<BinInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinInfoViewHolder {
        val binding = ItemAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BinInfoViewHolder, position: Int) {
        holder.bind(biInfo[position])
    }

    override fun getItemCount(): Int {
        return biInfo.size
    }

    fun setData(newData: List<BinInfo>) {
        biInfo = newData
        notifyDataSetChanged()
    }
}
