package com.test.smartpost.presentation.fragments.courseFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentCourseBinding
import com.test.smartpost.domain.mainAndCourse.model.CourseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseFragment : Fragment(R.layout.fragment_course) {

    private val binding: FragmentCourseBinding by viewBinding()
    private lateinit var course : CourseModel
    private val viewModel : CourseViewModel by viewModels()

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
        viewModel.addCourseModel(course, requireContext(), findNavController())

    }

}