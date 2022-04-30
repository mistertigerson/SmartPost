package com.test.smartpost.presentation.fragments.personalFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.test.smartpost.R
import com.test.smartpost.domain.ProfileModel
import com.test.smartpost.databinding.FragmentEditPersonalBinding


class EditPersonalFragment : Fragment(R.layout.fragment_edit_personal) {
    private val binding: FragmentEditPersonalBinding by viewBinding()
    private lateinit var database: DatabaseReference


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveData.setOnClickListener {
            database =
                Firebase.database("https://makersalex-ee99d-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
            writeNewUser(
                binding.etAddName.toString(), binding.etAddSpeciality.toString(),
                binding.etAddCity.toString(), binding.etAddContact.toString(),
                binding.etAddEducation.toString(), binding.etAddExperience.toString()
            )
            findNavController().navigate(R.id.personalFragment)
        }

    }

    private fun writeNewUser(
        name: String?, speciality: String?, city: String?, contacts: String?,
        education: String?, experience: String?
    ) {
        val user = ProfileModel(null, name, speciality, city, contacts, education, experience)

        if (name.toString().isNotEmpty()) {
            if (name != null) {
                database.child(name).setValue(name)
            }
        } else {
            binding.etAddName.error = getString(R.string.et_add_name_error_rus)
        }
    }
}
