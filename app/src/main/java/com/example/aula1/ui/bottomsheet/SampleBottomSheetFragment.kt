package com.example.aula1.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aula1.databinding.BottomsheetDialogBinding
import com.example.aula1.ui.model.SampleHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SampleBottomSheetFragment(private val helper: SampleHelper) : BottomSheetDialogFragment() {

    private var _binding: BottomsheetDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheet()
        setListener()
    }

    private fun setBottomSheet() {
        //configurando os parâmetros da bottomsheet
        binding.run {
            androidTitle.text = helper.title
            androidDescription.text = helper.description
        }
    }

    private fun setListener() {
        //definindo a ação do botão da bottomsheet
        binding.button.setOnClickListener{
            dismiss()
        }
    }

    companion object {
        //criando a função que irá instanciar a bottomsheet
        fun newInstance(helper: SampleHelper): SampleBottomSheetFragment {
            return SampleBottomSheetFragment(helper)
        }
    }
}