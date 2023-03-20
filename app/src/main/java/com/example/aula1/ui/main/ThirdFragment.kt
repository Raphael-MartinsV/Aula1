package com.example.aula1.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.aula1.ui.bottomsheet.SampleBottomSheetFragment
import com.example.aula1.ui.main.compose.ThirdFragmentScreen
import com.example.aula1.ui.main.theme.ComposeTheme
import com.example.aula1.ui.model.SampleHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThirdFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSampleData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeTheme{
                    ThirdFragmentScreen(
                        viewModel = viewModel,
                        onClickIcon = { sampleHelper -> showBottomSheet(sampleHelper)}
                    )
                }
            }
        }
    }

    private fun showBottomSheet(sampleHelper: SampleHelper){
        //função instanciando um novo bottomSheet
        val sampleBottomSheetFragment: SampleBottomSheetFragment =
            SampleBottomSheetFragment.newInstance(sampleHelper)
        sampleBottomSheetFragment.show(
            childFragmentManager,
            "dialog_fragment"
        )
    }
}