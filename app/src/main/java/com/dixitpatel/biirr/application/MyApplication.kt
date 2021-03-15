package com.dixitpatel.biirr.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.dixitpatel.biirr.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * MyApplication class that provides functionality of Dagger injectors.
 * */
@HiltAndroidApp
class MyApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

        // If the build is on debug mode from Android studio then Logcat printed otherwise not to display.
        if (BuildConfig.DEBUG)
        {
            Timber.plant(Timber.DebugTree())
        }

        // App is Compatible with Night and Day mode.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}