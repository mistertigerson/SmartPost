package com.test.smartpost.presentation.fragments.educationFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.smartpost.domain.Response
import com.test.smartpost.domain.mainAndCourse.model.CourseModel
import com.test.smartpost.domain.mainAndCourse.usecases.GetListCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EducationFragmentViewModel @Inject constructor(private val getListCourseUseCase: GetListCourseUseCase) :
    ViewModel() {

    private val _getCourse = MutableStateFlow<Response<List<CourseModel>>>(Response.Loading())
    val getCourse = _getCourse.asStateFlow()

    init {
        getCourseList()
    }

    private fun getCourseList() {
        viewModelScope.launch {
            getListCourseUseCase.getListCourse().collect{
                _getCourse.value = it
            }
        }
    }
}