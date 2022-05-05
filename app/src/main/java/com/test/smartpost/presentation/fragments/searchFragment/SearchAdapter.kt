package com.test.smartpost.presentation.fragments.searchFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.smartpost.databinding.MainItemBinding
import com.test.smartpost.domain.mainAndCourse.model.CourseModel
import com.test.smartpost.extensions.loadImage

open class SearchAdapter() :
    ListAdapter<CourseModel, SearchAdapter.CourseHolder>(SearchDiffUtil()), Filterable {

    var courseList: ArrayList<CourseModel> = ArrayList()
    var courseListFilter = ArrayList<CourseModel>()


    var onItemClick: ((CourseModel) -> Unit)? = null

    inner class CourseHolder(private val viewBinding: MainItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(courseModel: CourseModel) {

            viewBinding.tvComments.text = courseModel.description
            viewBinding.tvNameOfAuthor.text = courseModel.nameOfAuthor
            viewBinding.tvTitle.text = courseModel.nameOfCourse
            viewBinding.ivIcon.loadImage(courseModel.image.toString())

        }
    }

    class SearchDiffUtil : DiffUtil.ItemCallback<CourseModel>() {
        override fun areItemsTheSame(oldItem: CourseModel, newItem: CourseModel): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CourseModel, newItem: CourseModel): Boolean =
            oldItem == newItem
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.CourseHolder {
        return CourseHolder(
            MainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) courseListFilter = courseList else {
                    val filteredList = ArrayList<CourseModel>()
                    courseList
                        .filter {
                            (it.nameOfCourse!!.contains(constraint!!)) or
                                    (it.nameOfAuthor?.contains(constraint)!!)

                        }
                        .forEach { filteredList.add(it) }
                    courseListFilter = filteredList

                }
                return FilterResults().apply { values = courseListFilter }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                courseListFilter = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<CourseModel>
                notifyDataSetChanged()
            }

        }
    }
}

