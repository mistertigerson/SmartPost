package com.test.smartpost.presentation.fragments.mainFragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.firestore.*
import com.test.smartpost.R
import com.test.smartpost.databinding.FragmentMainBinding
import com.test.smartpost.data.models.MainModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private var list: ArrayList<MainModel> = arrayListOf()
    private lateinit var mainModel: MainModel
    private lateinit var adapter: MainAdapter
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()


    }

    //инициализация адаптера
    private fun initAdapter() {
        db = FirebaseFirestore.getInstance()
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
        binding.recyclerMain.adapter = adapter
        readFireStoreData()


    }

    //стягивает данные с FireStore и сетит в лист
    fun readFireStoreData() {
        db.collection("course")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e(TAG, "FireStore error: " + error.message.toString())
                        return
                    }
                    for (document: DocumentChange in value?.documentChanges!!) {
                        if (document.type == DocumentChange.Type.ADDED) {
                            list.add(document.document.toObject(MainModel::class.java))
                            Log.e(TAG, "onEvent: $list")
                        }
                    }
                    adapter.notifyDataSetChanged()
                    adapter.setList(list)
                }

            })


    }

}