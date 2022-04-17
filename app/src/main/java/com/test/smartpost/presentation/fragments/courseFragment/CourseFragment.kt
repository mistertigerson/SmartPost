package com.test.smartpost.presentation.fragments.courseFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentCourseBinding


class CourseFragment : Fragment(R.layout.fragment_course) {

    private val binding : FragmentCourseBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreate.setOnClickListener{
            initDb()
        }
    }

    private fun initDb() {
        val db = FirebaseFirestore.getInstance()
        val course : MutableMap<String, Any> = HashMap()

        course["nameOfAuthor"] = binding.etNameOfAuthor.text.toString()
        course["nameOfCourse"] = binding.etNameOfCourse.text.toString()
        course["comment"] = binding.etComments.text.toString()

        db.collection("course")
            .add(course)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_courseFragment_to_personalFragment)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "fail", Toast.LENGTH_SHORT).show()
            }

    }

}