package com.test.smartpost.data.models

import java.io.Serializable

data class MainModel(
    var imageIcon: Int,
    var title: String,
    var NameOfAuthor: String,
    var comments: String,


    ) : Serializable