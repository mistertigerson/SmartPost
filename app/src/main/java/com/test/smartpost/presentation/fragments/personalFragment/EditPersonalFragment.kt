package com.test.smartpost.presentation.fragments.personalFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.test.smartpost.R
import com.test.smartpost.domain.personals.models.PersonalModel
import com.test.smartpost.databinding.FragmentEditPersonalBinding
import com.test.smartpost.extensions.loadImage
import com.test.smartpost.presentation.fragments.secondRegistration.*

const val CONTACTS = "CONTACTS"
const val CITY = "CITY"
const val PROFESSION = "PROFESSION"

class EditPersonalFragment : Fragment(R.layout.fragment_edit_personal) {
    private val binding: FragmentEditPersonalBinding by viewBinding()
    lateinit var preferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveData.setOnClickListener {
            preferences.edit().putString(SHARED_EXPERIENCE, binding.etAddExperience.text.toString())
                .putString(SHAREDetCareerDescription, binding.etAddSpeciality.text.toString())
                .putString(SHARED_NAME, binding.etName.text.toString())
                .putString(CITY, binding.etAddCity.text.toString())
                .putString(CONTACTS, binding.etAddContact.text.toString())
                .putString(PROFESSION, binding.etAddEducation.text.toString())
                .apply()
            findNavController().navigate(R.id.action_editPersonalFragment_to_personalFragment)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    }


}
