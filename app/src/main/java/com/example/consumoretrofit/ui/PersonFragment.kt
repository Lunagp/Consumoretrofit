package com.example.consumoretrofit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.consumoretrofit.R
import com.example.consumoretrofit.data.datasource.PersonasDataSource
import com.example.consumoretrofit.data.rest.RetrofitClient
import com.example.consumoretrofit.databinding.FragmentMainBinding
import com.example.consumoretrofit.databinding.FragmentPersonBinding
import com.example.consumoretrofit.presentacion.PersonViewModel
import com.example.consumoretrofit.presentacion.PersonViewModelFactory
import kotlinx.coroutines.flow.collect

class PersonFragment : Fragment(R.layout.fragment_person) {
    private lateinit var binding: FragmentPersonBinding

    private val viewModel by viewModels<PersonViewModel> { PersonViewModelFactory(
        PersonasDataSource(RetrofitClient.webService)
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding =  FragmentPersonBinding.bind(view)

        obtainData()
    }

    private fun obtainData() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.fetchPerson(id).collect{

                }
            }
        }
    }
}