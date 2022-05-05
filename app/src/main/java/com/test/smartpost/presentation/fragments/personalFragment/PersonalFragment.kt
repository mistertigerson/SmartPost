package com.test.smartpost.presentation.fragments.personalFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentPersonalBinding
import com.test.smartpost.domain.personals.models.PersonalModel
import com.test.smartpost.extensions.loadImage


class PersonalFragment : Fragment(R.layout.fragment_personal) {

    private val binding: FragmentPersonalBinding by viewBinding()
    private val args: PersonalFragmentArgs by navArgs()
    private lateinit var personalModel: PersonalModel
    private lateinit var personalModel2: PersonalModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initModel()
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_personalFragment_to_editPersonalFragment)
        }
        binding.tvAddCourse.setOnClickListener {
            findNavController().navigate(R.id.action_personalFragment_to_courseFragment)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    private fun initModel() {
        personalModel2 = args.personalModel.copy()
        binding.tvProfession.text = personalModel2.experience
        binding.tvCarrier.text = personalModel2.etCareerDescription
        binding.tvEmail.text = personalModel2.email
        binding.tvName.text = personalModel2.name
        val user = FirebaseAuth.getInstance().currentUser
        binding.ivAvatar.loadImage(user?.photoUrl.toString())


    }
}