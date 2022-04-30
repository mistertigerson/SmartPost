package com.test.smartpost

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        fbAuth = FirebaseAuth.getInstance()
    }

    companion object{
        lateinit var fbAuth: FirebaseAuth
        lateinit var authListener: FirebaseAuth.AuthStateListener

    }
}