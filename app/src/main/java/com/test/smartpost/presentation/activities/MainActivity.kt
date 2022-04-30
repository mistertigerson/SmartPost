package com.test.smartpost.presentation.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.test.smartpost.R
import com.test.smartpost.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var navController: NavController
    private val fragments = arrayListOf(
        R.id.mainFragment,
        R.id.personalFragment,
        R.id.searchFragment,
        R.id.educationFragment

    )

    override fun onStart() {
        super.onStart()
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            navController.navigate(R.id.mainFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavController()
    }

    private fun initNavController() {

        val navHostController = supportFragmentManager.findFragmentById(R.id.navHostFragment)
        navController = navHostController!!.findNavController()
        binding.bottomNav.setupWithNavController(navController)

        //проверка на то что есть такой юзер или нет


        //скрыть bottomNavigation
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (fragments.contains(destination.id)) {
                binding.bottomNav.visibility = View.VISIBLE
            } else binding.bottomNav.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.e("TAG", "onBackPressed: ")
        navController.navigate(R.id.mainFragment)


    }
}