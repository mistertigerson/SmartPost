package com.test.smartpost.data.models

import java.io.Serializable

data class MainModel(
    var comment: String? = "",
    var nameOfAuthor: String? = "",
    var nameOfCourse: String? = "",


    ) : Serializable