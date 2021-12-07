package com.meetSuccess.FoodResturant.di

import android.app.Application

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {

    private val appRunning = false

    override fun onCreate() {
        super.onCreate()
       // ProductDatabase.getContactDatabase(this) //--AppDatabase_Impl does not exist
    }
}