package com.example.aula1.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aula1.R
import com.example.aula1.databinding.FragmentSecondBinding
import com.example.aula1.ui.bottomsheet.SampleBottomSheetFragment
import com.example.aula1.ui.model.SampleHelper
import com.example.aula1.ui.model.setInfoHelper
import com.example.aula1.ui.utils.NotificationUtils
import com.example.aula1.ui.utils.sampleConst1
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.txtLiveData.text = it
        }
        lifecycleScope.launchWhenCreated {
            viewModel.stateFlow.collectLatest {
                binding.txtStateFlow.text = it
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.sharedFlow.collectLatest {
                binding.txtSharedFlow.text = it
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        binding.btnNotification.setOnClickListener {
            //chama a função que irá criar a notificação com um botão
            NotificationUtils.notificationWithButtonAction(requireContext())
        }
        binding.buttonLiveData.setOnClickListener {
            //seta um novo helper com titulo e descrição
            val helper: SampleHelper? = setInfoHelper()[sampleConst1]
            if (helper != null) {
                //passa como parâmetro o novo helper pra exibilo na bottomsheet
                showBottomSheet(helper)
            }
        }
        binding.buttonStateFlow.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
        binding.buttonFlow.setOnClickListener {
            lifecycleScope.launch {
                viewModel.triggerFlow().collectLatest {
                    binding.txtFlow.text = it
                }
            }
        }
        binding.buttonSharedFlow.setOnClickListener {
            viewModel.triggerSharedFlow()
        }
    }

    private fun showBottomSheet(sampleHelper: SampleHelper) {
        //função instanciando um novo bottomSheet
        val sampleBottomSheetFragment: SampleBottomSheetFragment =
            SampleBottomSheetFragment.newInstance(sampleHelper)
        sampleBottomSheetFragment.show(
            childFragmentManager,
            "dialog_fragment"
        )
    }

    fun showKeyboard(context: Context) {
        //função que força exibir o teclado baseado no contexto atual
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    fun hideKeyboard(context: Context) {
        //função que força a esconder o teclado baseado no contexto atual
        try {
            (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            if (context.currentFocus != null && context.currentFocus!!.windowToken != null){
                (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    context.currentFocus!!.windowToken, 0
                )
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}