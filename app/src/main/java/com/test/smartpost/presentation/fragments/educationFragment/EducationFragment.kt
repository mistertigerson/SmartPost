package com.test.smartpost.presentation.fragments.educationFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentEducationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EducationFragment : Fragment(R.layout.fragment_education) {

    private val binding: FragmentEducationBinding by viewBinding()
    private val viewModel : EducationFragmentViewModel by viewModels()
    private val adapter : EducationAdapter by lazy {
        EducationAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setUpObservers()
        setupListeners()
    }

    private fun setupListeners() {
        adapter.onItemClick = {
            findNavController().navigate(
                EducationFragmentDirections.actionEducationFragmentToDetailsFragment(
                    it
                )
            )
        }
        adapter.onItemClick2 = {
            Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            viewModel.getCourse.collectLatest {
                adapter.submitList(it.data)
                Log.e("TAG", "setUpObservers: ${it.data}")
            }
        }
    }

    private fun initUI() {
        binding.rvEdu.apply {
            adapter = this@EducationFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }


}