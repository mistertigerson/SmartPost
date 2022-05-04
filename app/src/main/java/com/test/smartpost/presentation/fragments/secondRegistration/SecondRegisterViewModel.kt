package com.test.smartpost.presentation.fragments.secondRegistration

import androidx.lifecycle.ViewModel
import com.test.smartpost.data.ImgRepositoryImpl
import com.test.smartpost.domain.personals.models.ModelImg
import com.test.smartpost.domain.personals.useCases.AddImgUseCase
import com.test.smartpost.domain.personals.useCases.DeleteImgUseCase
import com.test.smartpost.domain.personals.useCases.GetImgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondRegisterViewModel @Inject constructor(private val repository : ImgRepositoryImpl) : ViewModel() {

    private val addImgUseCase = AddImgUseCase(repository)

    private val getImgUseCase = GetImgUseCase(repository)

    private val deleteImgUseCase = DeleteImgUseCase(repository)

    val imgList = getImgUseCase.getImg()

    fun addImgItem(img: ModelImg) {
        addImgUseCase.addImg(img)
    }

    fun deleteImg(img: ModelImg) {
        deleteImgUseCase.deleteImg(img)
    }
}