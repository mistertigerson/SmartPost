package com.test.smartpost.presentation.fragments.mainFragment

import androidx.recyclerview.widget.DiffUtil
import com.test.smartpost.domain.main.model.CourseModel

class MainDiffUtil: DiffUtil.ItemCallback<CourseModel>() {
    override fun areItemsTheSame(oldItem: CourseModel, newItem: CourseModel) = oldItem.nameOfCourse == newItem.nameOfCourse

    override fun areContentsTheSame(oldItem: CourseModel, newItem: CourseModel) = oldItem == newItem
}