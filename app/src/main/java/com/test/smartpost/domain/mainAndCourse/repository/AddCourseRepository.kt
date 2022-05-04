package com.test.smartpost.domain.mainAndCourse.repository

import android.content.Context
import androidx.navigation.NavController
import com.test.smartpost.domain.mainAndCourse.model.CourseModel


interface AddCourseRepository {

    fun addCourseModel(courseModel: CourseModel, context: Context, findNavController: NavController)
}