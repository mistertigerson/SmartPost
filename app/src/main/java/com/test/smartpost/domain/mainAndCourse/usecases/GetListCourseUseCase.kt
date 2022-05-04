package com.test.smartpost.domain.mainAndCourse.usecases

import com.test.smartpost.domain.mainAndCourse.repository.MainRepository
import javax.inject.Inject

class GetListCourseUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun getListCourse() = mainRepository.getListModel()

}