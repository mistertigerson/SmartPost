package com.test.smartpost.domain.personals.useCases

import com.test.smartpost.domain.personals.repository.ImgRepository
import com.test.smartpost.domain.personals.models.ModelImg
import javax.inject.Inject


class AddImgUseCase @Inject constructor(private val repository: ImgRepository) {
    fun addImg(img: ModelImg) {
        repository.addImg(img)
    }
}