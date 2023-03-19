package com.example.aula1.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.aula1.R
import com.example.aula1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = Bundle()
        setNavGraph(R.id.mainFragment, bundle)
    }

    private fun setNavGraph(startDestination: Int, args: Bundle? =null) {
        //configurando a navegação
        //passando o fragment inicial e os arumentos iniciais (no caso não temos nenhum)
        binding.run {
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.navigation)
            graph.setStartDestination(startDestination)
            navHostFragment.navController.setGraph(graph, args)
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "Message"
    }
}