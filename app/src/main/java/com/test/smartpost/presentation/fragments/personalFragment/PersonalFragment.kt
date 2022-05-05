package com.test.smartpost.presentation.fragments.personalFragment

import android.content.Context
import android.content.SharedPreferences
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
import com.test.smartpost.presentation.fragments.secondRegistration.*


class PersonalFragment : Fragment(R.layout.fragment_personal) {

    private val binding: FragmentPersonalBinding by viewBinding()
    private var args: PersonalFragmentArgs? = null
    lateinit var preferences: SharedPreferences


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
        args = arguments?.let { PersonalFragmentArgs.fromBundle(it) }
        preferences = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    }


    private fun initModel() {
        Log.e("TAG", "initModel: ${preferences.getString("experience", null)} ")
        binding.tvProfession.text = preferences.getString(SHARED_EXPERIENCE, "null")
        binding.tvCarrier.text = preferences.getString(SHAREDetCareerDescription, "null")
        binding.tvEmail.text = preferences.getString(EMAIL, "null")
        binding.tvName.text = preferences.getString(SHARED_NAME, "null")
        binding.tvCity.text = preferences.getString(CITY, "null")
        binding.tvContacts.text = preferences.getString(CONTACTS, "null")


        val user = FirebaseAuth.getInstance().currentUser
        binding.ivAvatar.loadImage(user?.photoUrl.toString())

    }
}