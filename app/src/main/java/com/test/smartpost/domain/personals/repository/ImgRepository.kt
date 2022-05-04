package com.test.smartpost.domain.personals.repository

import androidx.lifecycle.LiveData
import com.test.smartpost.domain.personals.models.ModelImg

interface ImgRepository {
    fun addImg(img: ModelImg)
    fun getImg(): LiveData<List<ModelImg>>
    fun deleteImg(img: ModelImg)
}