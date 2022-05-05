package com.test.smartpost.presentation.fragments.searchFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModels()

    private val adapter: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
        setUpSearchBar()
    }

    private fun setUpSearchBar() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                updateSearch()
            }
        })
    }

    private fun updateSearch() {
        val s = binding.searchBar.text

        if (s?.length == 0) {
            // Пользователь очистил поле поиска. Показываем все предметы
            // Загружаем в адаптер лист со всеми предметами
            lifecycleScope.launch {
                viewModel.getCourse.collectLatest {
                    adapter.submitList(it.data)
//                it.data?.let { it1 -> list.addAll(it1) }
                }
            }
        } else {
            // Пользователь что-то ввёл. Делаем поиск по этому запросу
            // Загружаем в адаптер отфильтрованный лист
            lifecycleScope.launch {
                viewModel.getCourse.collectLatest {
                    adapter.submitList(it.data?.filter { list ->
                        list.nameOfAuthor?.startsWith(s.toString(), true) == true
                                || list.nameOfCourse?.contains(s.toString(), true) ?: true
                    })
//                it.data?.let { it1 -> list.addAll(it1) }
                }
            }
        }
    }


    private fun setupUI() {
        binding.recyclerSearch.apply {
            adapter = this@SearchFragment.adapter
            layoutManager = LinearLayoutManager(context)

        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.getCourse.collectLatest {
                adapter.submitList(it.data)
//                it.data?.let { it1 -> list.addAll(it1) }
            }
        }
    }
}