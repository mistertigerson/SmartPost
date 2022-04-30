package com.test.smartpost.domain.main.usecases

import androidx.lifecycle.LiveData
import com.test.smartpost.domain.main.repository.MainRepository
import com.test.smartpost.domain.main.model.CourseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCourseUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun getListCourse() = mainRepository.getListModel()

}