package com.test.smartpost.presentation.fragments.personalFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentPersonalBinding


class PersonalFragment : Fragment(R.layout.fragment_personal) {

    private val binding: FragmentPersonalBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_personalFragment_to_editPersonalFragment)
        }

    }
}