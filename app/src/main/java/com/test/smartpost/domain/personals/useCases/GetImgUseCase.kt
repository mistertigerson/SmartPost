package com.test.smartpost.domain.personals.useCases

import com.test.smartpost.domain.personals.repository.ImgRepository
import javax.inject.Inject


class GetImgUseCase @Inject constructor(private val repository: ImgRepository) {

    fun getImg()=repository.getImg()

}