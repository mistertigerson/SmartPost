package com.test.smartpost.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.smartpost.domain.personals.repository.ImgRepository
import com.test.smartpost.domain.personals.models.ModelImg
import javax.inject.Inject


class ImgRepositoryImpl @Inject constructor() : ImgRepository {
    private val galleryListLD = MutableLiveData<List<ModelImg>>()
    private val galleryList = sortedSetOf<ModelImg>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0
    override fun addImg(img: ModelImg) {
        if (img.id == ModelImg.UNDEFINED_ID) {
            img.id = autoIncrementId++
        }
        galleryList.add(img)
        galleryUpdateList()
    }
    override fun getImg(): LiveData<List<ModelImg>> {
        return galleryListLD
    }
    override fun deleteImg(img: ModelImg) {
        galleryList.remove(img)
        galleryUpdateList()
    }
    private fun galleryUpdateList() {
        galleryListLD.value = galleryList.toList()
    }
}