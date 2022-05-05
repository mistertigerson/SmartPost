package com.test.smartpost.presentation.fragments.mainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.smartpost.databinding.MainItemBinding
import com.test.smartpost.domain.mainAndCourse.model.CourseModel
import com.test.smartpost.extensions.loadImage

class MainAdapter :
    ListAdapter<CourseModel, MainAdapter.MainViewHolder>(MainDiffUtil()) {

    var onItemClick: ((CourseModel) -> Unit)? = null
    var onItemClick2: ((CourseModel) -> Unit)? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            return MainViewHolder(
                MainItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class MainViewHolder(private val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(courseModel: CourseModel) {

            binding.tvComments.text = courseModel.description
            binding.tvNameOfAuthor.text = courseModel.nameOfAuthor
            binding.tvTitle.text = courseModel.nameOfCourse
            binding.ivIcon.loadImage(courseModel.image.toString())

            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(absoluteAdapterPosition))
            }
            binding.btnBuy.setOnClickListener{
                onItemClick2?.invoke(getItem(absoluteAdapterPosition))
            }
        }

    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                if (charSearch.isEmpty()) {
//                    list = countryList
//                } else {
//                    val resultList = ArrayList<String>()
//                    for (row in countryList) {
//                        if (row.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
//                            resultList.add(row)
//                        }
//                    }
//                    countryFilterList = resultList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = countryFilterList
//                return filterResults
//            }
//
//            @Suppress("UNCHECKED_CAST")
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                countryFilterList = results?.values as ArrayList<String>
//                notifyDataSetChanged()
//            }
//
//        }
//
//    }

}