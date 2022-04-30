package com.test.smartpost.presentation.fragments.courseFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentCourseBinding
import com.test.smartpost.domain.main.model.CourseModel


class CourseFragment : Fragment(R.layout.fragment_course) {

    private val binding: FragmentCourseBinding by viewBinding()
    private lateinit var course : CourseModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreate.setOnClickListener {
            initDb()
        }
    }

    private fun initDb() {
        Log.e("TAG", "initDb: ", )
        course = CourseModel(
            binding.etNameOfCourse.text.toString(),
            binding.etNameOfAuthor.text.toString(),
            binding.etDescription.text.toString(),
            binding.etProfession.text.toString(),
            binding.etInformationFree.text.toString(),
            binding.etInformation.text.toString(),
            binding.etImage.text.toString(),
            binding.etDescription.text.toString(),
            binding.etYoutube.text.toString(),
            binding.etHomework.text.toString(),
            binding.etTest.text.toString(),
            binding.etPrice.text.toString(),
        )
        val db = FirebaseFirestore.getInstance()
        db.collection("course")
            .add(course)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_courseFragment_to_personalFragment)
                    Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "failure", Toast.LENGTH_SHORT).show()
                }
            }

    }

}