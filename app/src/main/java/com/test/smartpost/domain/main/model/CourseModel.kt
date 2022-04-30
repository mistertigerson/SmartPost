package com.test.smartpost.domain.main.model

import java.io.Serializable

data class CourseModel(
    val nameOfCourse: String? = null,
    val nameOfAuthor: String? = null,
    val description: String? = null,
    val profession : String? = null,
    val informationFree : String? = null,
    val information : String? = null,
    val image: String? = null,
    val videoFromYouTube: String? = null,
    val lecture: String? = null,
    val homeWork : String? = null,
    val test: String? = null,
    val price : String? = null,
    var id : Int = UNDEFINED_ID
    ) : Serializable
{
    companion object{
        const val UNDEFINED_ID = -1
    }
}

