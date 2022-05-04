package com.test.smartpost.presentation.fragments.searchFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.test.smartpost.domain.Response
import com.test.smartpost.domain.mainAndCourse.model.CourseModel
import com.test.smartpost.domain.mainAndCourse.usecases.GetListCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val getListCourseUseCase: GetListCourseUseCase) :
    ViewModel() {

    private val _getCourse = MutableStateFlow<Response<List<CourseModel>>>(Response.Loading())
    val getCourse = _getCourse.asStateFlow()
    private val TAG = SearchViewModel::class.qualifiedName
    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData()
    var onResponse: MutableLiveData<Resource<List<CourseModel>>> = MutableLiveData()

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