package com.example.aula1.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.aula1.MyApp
import com.example.aula1.R
import com.example.aula1.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSampleData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
        setObservers()
        MyApp.prefs?.intExamplePref = 8
        binding.message.isVisible = false
    }

    private fun initFragment(){
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_SecondFragment)
        }
    }

    private fun setObservers() {
        viewModel.sampleData.observe(viewLifecycleOwner){
            binding.message.isVisible = true
            binding.message.text = it[0].title
        }
        viewModel.sampleError.observe(viewLifecycleOwner){
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
    }

}