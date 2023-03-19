package com.example.aula1.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aula1.MyApp
import com.example.aula1.R
import com.example.aula1.databinding.FragmentMainBinding
import com.example.aula1.ui.adapter.TableInfoAdapter
import com.example.aula1.ui.bottomsheet.SampleBottomSheetFragment
import com.example.aula1.ui.model.SampleHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: TableInfoAdapter

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
    }

    private fun initFragment(){
        binding.button.setOnClickListener {
            //navegação da primeira tela pra segunda tela
            findNavController().navigate(R.id.action_MainFragment_to_SecondFragment)
        }
    }

    private fun setObservers() {
        viewModel.sampleData.observe(viewLifecycleOwner){
            //Quando retornar os dados da chamada de api iremos setar o adapter com os dados
            adapter = TableInfoAdapter(it)
            //setar o listener referente ao adapter
            setListenerAdapter()
            //vincular o adapter ao declarado no xml
            binding.tableInfoItems.adapter = adapter
            //configurar o layoutManager
            binding.tableInfoItems.layoutManager = LinearLayoutManager(context)
        }
        viewModel.sampleError.observe(viewLifecycleOwner){
            //trativa de erro da chamada de api
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setListenerAdapter(){
        adapter.onClick = { helper ->
            if(helper != null) {
                showBottomSheet(helper)
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