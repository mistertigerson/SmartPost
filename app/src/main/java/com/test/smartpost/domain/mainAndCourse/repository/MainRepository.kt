package com.test.smartpost.domain.mainAndCourse.repository

import com.test.smartpost.domain.Response
import com.test.smartpost.domain.mainAndCourse.model.CourseModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getListModel() : Flow<Response<List<CourseModel>>>
}