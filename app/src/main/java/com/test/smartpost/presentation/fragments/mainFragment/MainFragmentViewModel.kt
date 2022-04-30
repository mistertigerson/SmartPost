package com.test.smartpost.presentation.fragments.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.smartpost.data.MainRepositoryImpl
import com.test.smartpost.domain.Response
import com.test.smartpost.domain.main.model.CourseModel
import com.test.smartpost.domain.main.usecases.AddCourseModelUseCase
import com.test.smartpost.domain.main.usecases.GetCourseModelUseCase
import com.test.smartpost.domain.main.usecases.GetListCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val getListCourseUseCase: GetListCourseUseCase) :
    ViewModel() {

    private val _getCourse = MutableStateFlow<Response<List<CourseModel>>>(Response.Loading())
    val getCourse = _getCourse.asStateFlow()

    init {
        getCourseList()
    }

    private fun getCourseList() {
        viewModelScope.launch {
            getListCourseUseCase.getListCourse().collect {
                _getCourse.value = it
            }
        }
    }
}