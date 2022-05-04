package com.test.smartpost.presentation.fragments.secondRegistration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.smartpost.R
import com.test.smartpost.domain.personals.models.ModelImg
import com.test.smartpost.extensions.loadImage

class RegisterAdapter : ListAdapter<ModelImg, RegisterAdapter.SecondViewHolder>(
    ImgDiffCall()
) {

    var imgOnLongListener: ((ModelImg) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        return SecondViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.certificate_image_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        val imgItem = getItem(position)
        holder.ivImg.loadImage(imgItem.photo)
        holder.itemView.setOnLongClickListener {
            imgOnLongListener?.invoke(imgItem)
            true
        }
    }

    class SecondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImg: ImageView = itemView.findViewById(R.id.imgCertificate)
    }
}