package com.test.smartpost.presentation.fragments.secondRegistration

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentSecondRegistrarionBinding
import com.test.smartpost.domain.personals.models.ModelImg
import com.test.smartpost.domain.personals.models.PersonalModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SecondRegistrationFragment : Fragment(R.layout.fragment_second_registrarion) {

    private val binding: FragmentSecondRegistrarionBinding by viewBinding()

    private val viewModel: SecondRegisterViewModel by viewModels()
    private lateinit var adapterSecond: RegisterAdapter
    private val bundle: Bundle = Bundle()


    private val registerForActivitySecond =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                if (it != null) {
                    val count: Int = it.data?.clipData!!.itemCount
                    for (i in 0 until count) {
                        viewModel.addImgItem(ModelImg(it.data?.clipData?.getItemAt(i)?.uri.toString()))
                    }
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()
        initListeners()

    }

    private fun pickImg() {
        Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).apply {
            type = "image/*"
            registerForActivity.launch(this)
        }
    }

    private fun initObservers() {
        viewModel.imgList.observe(viewLifecycleOwner) {
            adapterSecond.submitList(it)
        }
    }

    private fun initRecycler() {
        binding.recyclerSecond.apply {
            adapterSecond = RegisterAdapter()
            adapter = adapterSecond
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val filePath: Uri = it.data!!.data!!
                Glide.with(requireContext()).load(filePath).into(binding.imgSecondFragment)
            }
        }

    private fun pickCertificate() {
        Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).apply {
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            type = "image/*"
            registerForActivitySecond.launch(this)
        }
    }

    private fun initListeners() {
        adapterSecond.imgOnLongListener = {
            viewModel.deleteImg(it)
        }
        binding.btnAdd.setOnClickListener {
            pickCertificate()
        }

        binding.imgSecondFragment.setOnClickListener {
            pickImg()
        }
        binding.btnSave.setOnClickListener {
            sendData()
        }

    }

    private fun sendData() {
        val user = FirebaseAuth.getInstance().currentUser
        val personalModel = PersonalModel(
            experience = binding.etExperience.text.toString(),
            etCareerDescription = binding.etCareerDescription.text.toString(),
            name = user?.displayName,
            email = user?.email.toString(),
            )
        val bundle = Bundle()
        bundle.putSerializable("real", personalModel)
        val db = FirebaseFirestore.getInstance()
        db.collection("personal")
            .add(personalModel)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.personalFragment, bundle)
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show()
                }
            }

    }

}