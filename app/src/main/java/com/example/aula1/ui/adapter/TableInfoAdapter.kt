package com.example.aula1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.aula1.databinding.SampleInfoTableItemsBinding
import com.example.aula1.domain.model.SampleModel
import com.example.aula1.ui.model.SampleHelper

class TableInfoAdapter(
    private val tableInfoItems: List<SampleModel>
): RecyclerView.Adapter<TableInfoAdapter.TableInfoItemViewHolder>() {

    var onClick: ((SampleHelper?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableInfoItemViewHolder {
        return TableInfoItemViewHolder(
            SampleInfoTableItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = tableInfoItems.size

    override fun onBindViewHolder(holder: TableInfoItemViewHolder, position: Int) {
        holder.bind(tableInfoItems[position])
    }

    inner class TableInfoItemViewHolder(
        private val binding: SampleInfoTableItemsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(tableInfoItem: SampleModel) {
            binding.run {
                androidTitle.text = tableInfoItem.title
                androidText.text = tableInfoItem.body
                androidImage.isVisible = true
                androidImage.setOnClickListener{
                    onClick?.invoke(tableInfoItem.infoHelper)
                }
            }
        }
    }
}