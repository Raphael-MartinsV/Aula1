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

    //criando função lambda que irá realizar o evento de click
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

    //determina a quantidade de elementos do adapter
    override fun getItemCount(): Int = tableInfoItems.size

    //irá setar os dados referentes ao xml pra cada posição do adapter
    override fun onBindViewHolder(holder: TableInfoItemViewHolder, position: Int) {
        holder.bind(tableInfoItems[position])
    }

    //criando a função interna que irá setar os dados referente ao xml
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