package com.test.smartpost.presentation.fragments.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentMainBinding
import com.test.smartpost.presentation.models.MainModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private var list: ArrayList<MainModel> = ArrayList()
    private lateinit var mainModel: MainModel
    private lateinit var adapter: MainAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createModel()
        initAdapter()


    }

    private fun initAdapter() {

        adapter = MainAdapter(object : MainAdapter.ClickOnPlaylist {
            override fun onClick(model: MainModel, position: Int) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailsFragment(
                        list[position]
                    )
                )

            }

            override fun clickBtn() {
                findNavController().navigate(R.id.authFragment)
            }
        })
        adapter.setList(list)

        binding.recyclerMain.adapter = adapter


    }

    //создание модельки и добавление в лист
    private fun createModel() {
        mainModel = MainModel(
            R.drawable.ic_launcher_background,
            "Java",
            "Macconey",
            "the best course"
        )
        list.add(mainModel)
        mainModel = MainModel(
            R.drawable.ic_launcher_background,
            "Kotlin",
            "Macconey",
            "the best course"
        )
        list.add(mainModel)
        mainModel = MainModel(
            R.drawable.ic_launcher_background,
            "CSharp",
            "Macconey",
            "the best course"
        )
        list.add(mainModel)
    }
}