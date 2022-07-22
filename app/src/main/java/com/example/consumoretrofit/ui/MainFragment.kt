package com.example.consumoretrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.consumoretrofit.R
import com.example.consumoretrofit.data.datasource.PersonasDataSource
import com.example.consumoretrofit.data.rest.RetrofitClient
import com.example.consumoretrofit.databinding.FragmentMainBinding
import com.example.consumoretrofit.presentacion.PersonasViewModel
import com.example.consumoretrofit.presentacion.PersonasViewModelFactory
import com.example.consumoretrofit.core.Result


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    private val viewModel by viewModels<PersonasViewModel> { PersonasViewModelFactory(
        PersonasDataSource(RetrofitClient.webService)
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        obtainData()
    }

    private fun obtainData() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.fetchPersonas().collect{
                    when(it){
                        is Result.Loading->{
                            Log.e("Cargando","cargando" )
                        }
                        is Result.Success->{
                            Log.e("succes",it.data.toString() )
                        }
                        is Result.Failure->{
                            Log.e("Error",it.exception.toString())
                        }
                    }
                }
            }
        }
    }
}
