package com.test.smartpost.presentation.fragments.mainFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.smartpost.databinding.MainItemBinding
import com.test.smartpost.presentation.models.MainModel
import kotlin.properties.Delegates

class MainAdapter(private val clickOnPlaylist: ClickOnPlaylist) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var binding: MainItemBinding
    private val list: ArrayList<MainModel> = arrayListOf()
    var pos by Delegates.notNull<Int>()


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<MainModel>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(list[position])
        pos = holder.absoluteAdapterPosition

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MainViewHolder(itemView: MainItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun onBind(mainModel: MainModel) {
            binding.ivIcon.setImageResource(mainModel.imageIcon)
            binding.tvComments.text = mainModel.comments
            binding.tvNameOfAuthor.text = mainModel.NameOfAuthor
            binding.tvTitle.text = mainModel.title

            binding.root.setOnClickListener {
                clickOnPlaylist.onClick(mainModel, pos)
            }
            binding.btnBuy.setOnClickListener {
                clickOnPlaylist.clickBtn()
            }

        }

    }


    interface ClickOnPlaylist {
        fun onClick(model: MainModel, position: Int)
        fun clickBtn()
    }
}