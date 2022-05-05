package com.test.smartpost.presentation.fragments.searchFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentSearchBinding
import com.test.smartpost.domain.mainAndCourse.model.CourseModel
import com.test.smartpost.presentation.fragments.mainFragment.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModels()
//    private var list: ArrayList<CourseModel> = mutableListOf<CourseModel>()
    private val adapterRV: MainAdapter by lazy {
        MainAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
//        setupObservers()
        setUpSearchBar()
    }

    private fun setUpSearchBar() {

//        binding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                adapterRV.
//            }
//
//        })
//        binding.etSearchBar.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                updateSearch()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//
//        })
    }

//    private fun updateSearch() {
//        val s = binding.etSearchBar.text
//
//        if (s?.length == 0) {
//            // Пользователь очистил поле поиска. Показываем все предметы
//            // Загружаем в адаптер лист со всеми предметами
//            adapterRV.submitList(list)
//
//        } else {
//            // Пользователь что-то ввёл. Делаем поиск по этому запросу
//            // Загружаем в адаптер отфильтрованный лист
//            adapterRV.submitList(list.filter {
//                it.nameOfAuthor?.startsWith(s.toString(), true)
//                    ?: || it . nameOfCourse ?. contains (s.toString(), true) ?: true
//            } as ArrayList<CourseModel>
//        }
//        adapterRV.notifyDataSetChanged()
//    }


    private fun setupUI() {
        binding.recyclerSearch.apply {
            adapter = adapterRV
            layoutManager = LinearLayoutManager(context)

        }
    }

//    private fun setupObservers() {
//
//
//        lifecycleScope.launch {
//
//            viewModel.getCourse.collectLatest {
//                Log.e("TAG", "setupObservers: ${it.data.toString()}")
//
//                adapterRV.submitList(it.data)
//                it.data?.let { it1 -> list.addAll(it1) }
//            }
//        }
//    }
}