package com.test.smartpost.presentation.fragments.detailsFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val binding: FragmentDetailsBinding by viewBinding()
    private val args : DetailsFragmentArgs by navArgs()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
//        binding.ivIcon.setImageResource(args.model.imageIcon)
        binding.tvComments.text = args.courseModel.description
        binding.tvTitle.text = args.courseModel.nameOfCourse
        binding.tvNameOfAuthor.text = args.courseModel.nameOfAuthor
    }
}