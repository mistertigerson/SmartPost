package com.test.smartpost.domain.main.repository

import androidx.lifecycle.LiveData
import com.test.smartpost.domain.Response
import com.test.smartpost.domain.main.model.CourseModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getListModel() : Flow<Response<List<CourseModel>>>
}