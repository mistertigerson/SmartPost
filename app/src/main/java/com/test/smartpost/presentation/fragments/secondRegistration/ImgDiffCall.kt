package com.test.smartpost.presentation.fragments.secondRegistration

import androidx.recyclerview.widget.DiffUtil
import com.test.smartpost.domain.personals.models.ModelImg

class ImgDiffCall: DiffUtil.ItemCallback<ModelImg>(){
    override fun areItemsTheSame(oldItem: ModelImg, newItem: ModelImg): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ModelImg, newItem: ModelImg): Boolean {
        return oldItem == newItem
    }

}