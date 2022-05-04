package com.test.smartpost.presentation.fragments.searchFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentSearchBinding
import com.test.smartpost.domain.mainAndCourse.model.CourseModel
import com.test.smartpost.presentation.fragments.mainFragment.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), SearchView.OnQueryTextListener {
    private val binding: FragmentSearchBinding by viewBinding()
    private lateinit var recyclerView: RecyclerView
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter
    private val adapter: SearchAdapter by lazy {
        SearchAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setUpView()
//        doObserveWorks()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setUpView() {
//        recyclerView.findViewById<RecyclerView>(R.id.rv_search)
//        binding.barSearch.setOnQueryTextListener(this)
       recyclerView.apply {
            adapter = this.adapter
            layoutManager = LinearLayoutManager(context)
        }




    }

    private fun doObserveWorks() {
        lifecycleScope.launch {
            viewModel.getCourse.collectLatest {
                adapter.addData(it.data)

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderPhotosList(photosList: List<CourseModel>) {
        searchAdapter.addData(photosList)
        searchAdapter.notifyDataSetChanged()

    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        searchAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchAdapter.filter.filter(newText)
        return false
    }


}